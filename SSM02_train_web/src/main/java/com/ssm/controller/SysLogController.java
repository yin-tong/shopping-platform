package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.SysLog;
import com.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISyslogService syslogService;

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = syslogService.findAll(page,size);
        //分页bean
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        int sysLogCnt = syslogService.findCnt();
        mv.addObject("sysLogCnt",sysLogCnt);
        mv.setViewName("syslog-list");
        return mv;
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "ids") String[] ids){
        if(ids==null){
            return "redirect:/sysLog/findAll";
        }
        syslogService.deleteSysLogById(ids);
        return "redirect:/sysLog/findAll";
    }

    @RequestMapping("/findSysLogLikeUserName")
    public ModelAndView findSysLogLikeUserName(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "username") String username) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<SysLog> sysLogList = syslogService. findSysLogLikeUserName(page,size,username);
        //分页bean
        PageInfo pageInfo = new PageInfo(sysLogList);
        mv.addObject("pageInfo",pageInfo);
        int sysLogCnt = syslogService.findCnt();
        mv.addObject("sysLogCnt",sysLogCnt);
        mv.setViewName("syslog-list");
        return mv;
    }

}
