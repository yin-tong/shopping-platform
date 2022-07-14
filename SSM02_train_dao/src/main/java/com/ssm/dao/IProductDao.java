package com.ssm.dao;

import com.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductDao {


    @Select("select * from product where id = #{id}")
    Product findById(String id);

    @Select("select * from product")
    List<Product> findAll();

    @Insert("insert into product(productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus,quantity) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus},#{quantity})")
    void saveProduct(Product product);

    @Select("select count(*) from product")
    int findCnt();


    @Delete("delete from product where id = #{id}")
    void deleteProduct(String productId);

    @Select("select * from product where productName like '%${name}%'")
    List<Product> findByName(@Param(value = "name") String name);

    @Update("update product set productNum=#{productNum},productName=#{productName},cityName=#{cityName},departureTime=#{departureTime}, productPrice=#{productPrice},productDesc=#{productDesc},productStatus=#{productStatus},quantity=#{quantity} where id=#{id}")
    void update(Product product);

    @Select("select * from product where quantity>0 and productStatus=1")
    List<Product> findCanBuy();

    @Update("update product set quantity=quantity-1 where id=#{id}")
    void reduceOneById(String id);

    @Update("update product set quantity=quantity+1 where id=#{id}")
    void addOneById(String id);

    @Select("select * from product where quantity>0 and productStatus=1 and productName like '%${name}%'")
    List<Product> userFindProductByName(@Param(value = "name")String name);
}
