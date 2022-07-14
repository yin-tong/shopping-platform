package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Product;
import com.ssm.domain.UserInfo;
import com.ssm.service.IOrdersService;
import com.ssm.service.IProductService;
import com.ssm.service.IUserService;
import com.ssm.utils.MoneyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @RequestMapping("/refundOk")
    public String refundOk(@RequestParam(name="id") String id){
        Order order = ordersService.findById(id);
        String username = userService.findById(order.getUserId()).getUsername();
        Double price = productService.findById(order.getProductId()).getProductPrice();
        order.setOrderStatus(3);
        ordersService.update(order);
        userService.addMoney(username,String.valueOf(price));
        productService.addOneById(order.getProductId());
        return "redirect:/path/toMain";
    }

    @RequestMapping("/refund")
    public String refund(@RequestParam(name="id") String id){
        Order order = ordersService.findById(id);
        order.setOrderStatus(0);
        ordersService.update(order);
        UserInfo userInfo = userService.findById(order.getUserId());
        return "redirect:/orders/findByUsername?username="+userInfo.getUsername();
    }

    @RequestMapping("/findByUsername")
    public ModelAndView findByUsername(@RequestParam(name="username") String username,@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo0 = userService.findByUserName(username);
        List<Order> ordersList = ordersService.findByUserId(userInfo0.getId(),page,size);
        for (Order order : ordersList) {
            Product product = productService.findById(order.getProductId());
            UserInfo userInfo = userService.findById(order.getUserId());
            order.setProduct(product);
            order.setUserInfo(userInfo);
        }

        //分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        int ordersCnt = ordersService.findCnt();
        mv.addObject("ordersCnt",ordersCnt);
        mv.setViewName("orders-look");
        return mv;
    }

    @RequestMapping("/save")
    public String save(@RequestParam(name = "productId")String productId,@RequestParam(name = "username")String username){
        UserInfo userInfo = userService.findByUserName(username);
        Order order = new Order();
        order.setOrderTime(new Date());
        order.setUserId(userInfo.getId());
        order.setProductId(productId);
        order.setPayType(1);
        order.setOrderStatus(1);
        float money = MoneyUtils.getMoney(username);
        float price = productService.findById(productId).getProductPrice().floatValue();
        if (money>=price){
            ordersService.save(order);
            productService.reduceOneById(productId);
            userService.reduceMoney(username,String.valueOf(price));
        }
        return "redirect:/product/buy?username="+username;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Order> ordersList = ordersService.findAll(page,size);
        for (Order order : ordersList) {
            Product product = productService.findById(order.getProductId());
            UserInfo userInfo = userService.findById(order.getUserId());
            order.setProduct(product);
            order.setUserInfo(userInfo);
        }

        //分页bean
        PageInfo pageInfo = new PageInfo(ordersList);
        mv.addObject("pageInfo",pageInfo);
        int ordersCnt = ordersService.findCnt();
        mv.addObject("ordersCnt",ordersCnt);
        mv.setViewName("orders-list");
        return mv;
    }

    @RequestMapping("/findById")
    public ModelAndView findById(String id){
        ModelAndView mv = new ModelAndView();
        Order order = ordersService.findById(id);
        Product product = productService.findById(order.getProductId());
        UserInfo userInfo = userService.findById(order.getUserId());
        order.setProduct(product);
        order.setUserInfo(userInfo);
        mv.addObject("order",order);
        mv.setViewName("order-show");
        return mv;
    }

    @RequestMapping("/delete")
    public ModelAndView delete(@RequestParam(name = "ids") String[] ids){
        ModelAndView mv = new ModelAndView();
        String message = null;
        if (ids.length==0){
            message = "订单不能为空";
        }else{
            boolean flag = true;
            for (String id : ids) {
                if (ordersService.findById(id).getOrderStatus()!=0){
                    ordersService.deleteById(id);
                }else{
                    message = "订单尚未完成，不能删除";
                }
            }
        }
        mv.setViewName("redirect:/orders/findAll");
        return mv;
    }

    @RequestMapping("/userDelete")
    public ModelAndView userDelete(@RequestParam(name = "ids") String[] ids,@RequestParam(name = "username") String username){
        ModelAndView mv = new ModelAndView();
        String message = null;
        if (ids.length==0){
            message = "订单不能为空";
        }else{
            boolean flag = true;
            for (String id : ids) {
                if (ordersService.findById(id).getOrderStatus()!=0){
                    ordersService.userDeleteById(id);
                }else{
                    message = "订单尚未完成，不能删除";
                }
            }
        }
        mv.setViewName("redirect:/orders/findByUsername?username="+username);
        return mv;
    }

    @RequestMapping("/findOrdersByUsername")
    public ModelAndView findOrdersByUsername(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "username") String username) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<Order> orders = ordersService.findOrdersByUsername(page,size,username);

        for (Order order : orders) {
            Product product = productService.findById(order.getProductId());
            UserInfo userInfo = userService.findById(order.getUserId());
            order.setProduct(product);
            order.setUserInfo(userInfo);
        }

        //分页bean
        PageInfo pageInfo = new PageInfo(orders);
        mv.addObject("pageInfo",pageInfo);
        int ordersCnt = ordersService.findCnt();
        mv.addObject("ordersCnt",ordersCnt);
        mv.setViewName("orders-list");
        return mv;
    }
}
