package com.ssm.service;

import com.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IProductService {

    /**
     * 查询所有商品
     * @return
     */
    List<Product> findAll(int page, int size);

    /**
     * 增加商品
     * @param product
     */
    void saveProduct(Product product);

    int findCnt();

    void deleteProduct(String[] productIds);

    List<Product> findByName(Integer page, Integer size,String name) throws UnsupportedEncodingException;

    Product findById(String id);

    void update(Product product);

    List<Product> findCanBuy(int page, int size);

    void reduceOneById(String id);

    void addOneById(String id);

    List<Product> userFindProductByName(Integer page, Integer size, String name) throws UnsupportedEncodingException;
}
