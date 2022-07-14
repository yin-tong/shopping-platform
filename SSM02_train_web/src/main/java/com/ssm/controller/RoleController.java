package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Permission;
import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import com.ssm.service.IPermissionService;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {


    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true) String roleId,@RequestParam(name = "permissionIds",required = true) String[] permissionIds){
        roleService.addPermissionToRole(roleId,permissionIds);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String roleId){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(roleId);
        List<Permission> permissions = permissionService.findOtherPermission(roleId);
        mv.addObject("role",role);
        mv.addObject("permissions",permissions);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){

        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findAll(page,size);
        //分页
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
        int roleCnt = roleService.findCnt();
        mv.addObject("roleCnt",roleCnt);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Role role){
        roleService.save(role);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "roleIds") String[] roleIds){
        roleService.deleteRoleById(roleIds);
        return "redirect:/role/findAll";
    }

    @RequestMapping("/findRolesLikeRoleDesc")
    public ModelAndView findRolesLikeRoleDesc(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "desc") String desc) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<Role> roles = roleService.findRolesLikeRoleDesc(page,size,desc);
        //分页
        PageInfo pageInfo = new PageInfo(roles);
        mv.addObject("pageInfo",pageInfo);
        int roleCnt = roleService.findCnt();
        mv.addObject("roleCnt",roleCnt);
        mv.setViewName("role-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Role role = roleService.findById(id);
        mv.addObject("role",role);
        mv.setViewName("role-show");
        return mv;
    }
}
