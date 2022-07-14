package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.dao.IPermissionDao;
import com.ssm.domain.Order;
import com.ssm.domain.Permission;
import com.ssm.domain.Product;
import com.ssm.domain.UserInfo;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findAll(page,size);
        int permissionCnt = permissionService.findCnt();
        //分页bean
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("permissionCnt",permissionCnt);
        mv.setViewName("permission-list");
        return mv;
    }

    @RequestMapping("/save")
    public String save(Permission permission){
        permissionService.save(permission);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/update")
    public String update(Permission permission){
        permissionService.update(permission);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "ids") String[] ids){
        permissionService.delete(ids);
        return "redirect:/permission/findAll";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(name = "id") String id){
        ModelAndView mv = new ModelAndView();
        Permission permission = permissionService.findById(id);
        mv.addObject("permission",permission);
        mv.setViewName("permission-show");
        return mv;
    }

    @RequestMapping("/findPermissionsLikeName")
    public ModelAndView findPermissionsLikeName(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "name")String name) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<Permission> permissions = permissionService.findPermissionsLikeName(page,size,name);
        int permissionCnt = permissionService.findCnt();
        //分页bean
        PageInfo pageInfo = new PageInfo(permissions);
        mv.addObject("pageInfo",pageInfo);
        mv.addObject("permissionCnt",permissionCnt);
        mv.setViewName("permission-list");
        return mv;
    }
}
