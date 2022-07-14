package com.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ssm.domain.Order;
import com.ssm.domain.Product;
import com.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/buy")
    public ModelAndView toBuy(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findCanBuy(page,size);
        //分页bean
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        int productCnt = products.size();
        mv.addObject("productCnt",productCnt);
        mv.setViewName("product-buy");
        return mv;
    }

    @RequestMapping("/update")
    public String update(Product product){
        System.out.println("===================================");
        System.out.println(product);
        productService.update(product);
        return "redirect:/product/show?productId="+product.getId();
    }

    @RequestMapping("/show")
    public ModelAndView show(@RequestParam(name = "productId") String productId){
        ModelAndView mv = new ModelAndView();
        Product product = productService.findById(productId);
        mv.addObject("product",product);
        mv.setViewName("product-update");
        return mv;
    }

    @RequestMapping("/findAll")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size){
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll(page,size);
        //分页bean
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        int productCnt = productService.findCnt();
        mv.addObject("productCnt",productCnt);
        mv.setViewName("product-page-list");
        return mv;
    }

    @RequestMapping("/save")
    public String saveProduct(Product product){
        System.out.println("========================================================");
        System.out.println("========================================================");
        System.out.println("========================================================");
        System.out.println(product);
        productService.saveProduct(product);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam(name = "productIds") String[] productIds){
        productService.deleteProduct(productIds);
        return "redirect:/product/findAll";
    }

    @RequestMapping("/findByName")
    public ModelAndView findByName(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "name",required = false) String name) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findByName(page,size,name);
        //分页bean
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        int productCnt = productService.findCnt();
        mv.addObject("productCnt",productCnt);
        mv.setViewName("product-page-list");
        return mv;
    }

    @RequestMapping("/userFindProductByName")
    public ModelAndView userFindProductByName(@RequestParam(name = "page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "15")Integer size,@RequestParam(name = "name",required = false) String name) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.userFindProductByName(page,size,name);
        //分页bean
        PageInfo pageInfo = new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        int productCnt = productService.findCnt();
        mv.addObject("productCnt",productCnt);
        mv.setViewName("product-buy");
        return mv;
    }


}
