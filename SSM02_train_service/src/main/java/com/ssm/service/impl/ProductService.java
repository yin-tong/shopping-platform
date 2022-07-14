package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IProductDao;
import com.ssm.domain.Product;
import com.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
public class ProductService implements IProductService {

    @Autowired
    IProductDao productDao;

    @Override
    public List<Product> findAll(int page,int size) {
        //从第一页开始，每页查询5个
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public int findCnt() {
        return productDao.findCnt();
    }

    @Override
    public void deleteProduct(String[] productIds) {
        for (String productId : productIds){
            productDao.deleteProduct(productId);
        }
    }

    @Override
    public List<Product> findByName(Integer page, Integer size, String name) throws UnsupportedEncodingException {
        //从第一页开始，每页查询5个
        PageHelper.startPage(page,size);
        return productDao.findByName(URLDecoder.decode(URLEncoder.encode(name,"iso8859-1"),"utf-8"));
    }

    @Override
    public Product findById(String id) {
        return productDao.findById(id);
    }

    @Override
    public void update(Product product) {
        productDao.update(product);
    }

    @Override
    public List<Product> findCanBuy(int page, int size) {
        //从第一页开始，每页查询5个
        PageHelper.startPage(page,size);
        return productDao.findCanBuy();
    }

    @Override
    public void reduceOneById(String id) {
        productDao.reduceOneById(id);
    }

    @Override
    public void addOneById(String id) {
        productDao.addOneById(id);
    }

    @Override
    public List<Product> userFindProductByName(Integer page, Integer size, String name) throws UnsupportedEncodingException {
        PageHelper.startPage(page,size);
        return productDao.userFindProductByName(URLDecoder.decode(URLEncoder.encode(name,"iso8859-1"),"utf-8"));
    }

}
