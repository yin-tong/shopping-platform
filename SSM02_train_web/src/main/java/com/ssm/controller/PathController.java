package com.ssm.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Product;
import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import com.ssm.service.IOrdersService;
import com.ssm.service.IProductService;
import com.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/path")
public class PathController {

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @RequestMapping("/toMain")
    public ModelAndView toMain(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            UserInfo currentuserInfo = userService.findByUserName(currentUserName);
            List<Role> roles = currentuserInfo.getRoles();
            for (Role role : roles) {
                if ("ADMIN".equals(role.getRoleName())){
                    List<Order> ordersList = ordersService.findRefund(page,size);
                    for (Order order : ordersList) {
                        Product product = productService.findById(order.getProductId());
                        UserInfo userInfo = userService.findById(order.getUserId());
                        order.setProduct(product);
                        order.setUserInfo(userInfo);
                    }

                    //分页bean
                    PageInfo pageInfo = new PageInfo(ordersList);
                    mv.addObject("pageInfo",pageInfo);
                    int refundCnt = ordersList.size();
                    int ordersCnt = ordersService.findPayCnt();
                    int productCnt = productService.findCnt();
                    int userCnt = userService.findCnt();
                    mv.addObject("refundCnt",refundCnt);
                    mv.addObject("ordersCnt",ordersCnt);
                    mv.addObject("productCnt",productCnt);
                    mv.addObject("userCnt",userCnt);
                    mv.setViewName("main");
                    return mv;
                }
                if ("USER".equals(role.getRoleName())){
                    List<Product> products = productService.findCanBuy(page,size);
                    //分页bean
                    PageInfo pageInfo = new PageInfo(products);
                    mv.addObject("pageInfo",pageInfo);
                    int productCnt = products.size();
                    mv.addObject("productCnt",productCnt);
                    mv.setViewName("product-buy");
                    return mv;
                }
            }
        }
        return mv;
    }
}
