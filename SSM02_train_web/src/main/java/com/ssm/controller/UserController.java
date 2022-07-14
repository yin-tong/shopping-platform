package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Product;
import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import com.ssm.service.IRoleService;
import com.ssm.service.IUserService;
import com.ssm.utils.BCryptPasswordEncoderUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/addMoney")
    public void addMoney(@RequestParam("username") String username,@RequestParam("money") String money){
        userService.addMoney(username,money);
    }

    @RequestMapping("/reduceMoney")
    public void reduceMoney(@RequestParam("username") String username,@RequestParam("money") String money){
        userService.reduceMoney(username,money);
    }

    @RequestMapping("/update")
    public String update(UserInfo userInfo){
        UserInfo userInfo1 = userService.findById(userInfo.getId());
        if (!userInfo1.getPassword().equals(userInfo.getPassword())){
            String password = BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword());
            userInfo.setPassword(password);
        }
        userService.update(userInfo);
        return "redirect:/user/personalInformation?username="+userInfo.getUsername();

    }

    @RequestMapping("/personalInformation")
    public ModelAndView personalInformation(@RequestParam(name = "username") String username){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo =  userService.findByUserName(username);;
        mv.addObject("user",userInfo);
        mv.setViewName("personalInformation");
        return mv;

    }

    @RequestMapping("/register")
    public String register(UserInfo userInfo){
        userInfo.setStatus(1);
        userService.save(userInfo);
        String[] roleIds = {"2a47e7a3439311ecaa6b005056c00001"};
        String userId = userService.findByUserName(userInfo.getUsername()).getId();
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/login.jsp";
    }

    @RequestMapping("/addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:/user/findAll";
    }

    /**
     * 根据用户Id查询用户以及用户可以添加的角色
     * @param userId
     * @return
     */
    @RequestMapping("/findUserByIdAndALlRole")
    public ModelAndView findUserByIdAndALlRole(@RequestParam(name = "id",required = true) String userId){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(userId);
        List<Role> otherRoles = roleService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findById(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findAll(page,size);
        //分页
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("pageInfo",pageInfo);
        int userCnt = userService.findCnt();
        mv.addObject("userCnt",userCnt);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(UserInfo userInfo){
        userService.save(userInfo);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "ids") String[] ids){
        userService.deleteUser(ids);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/open")
    public String open(@RequestParam(name = "ids") String[] ids){
        userService.open(ids);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/close")
    public String close(@RequestParam(name = "ids") String[] ids){
        userService.close(ids);
        return "redirect:/user/findAll";
    }

    @RequestMapping("/findUsersLikeUsername")
    public ModelAndView findUsersLikeUsername(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "username") String username) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userInfos = userService.findUsersLikeUsername(page,size,username);

        //分页bean
        PageInfo pageInfo = new PageInfo(userInfos);
        mv.addObject("pageInfo",pageInfo);
        int userCnt = userService.findCnt();
        mv.addObject("userCnt",userCnt);
        mv.setViewName("user-list");
        return mv;
    }

}
