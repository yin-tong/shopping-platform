package com.ssm.dao;

import com.ssm.domain.Order;
import com.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IOrdersDao {

//    @Select("select * from orders")
//    @Results(id = "ordersMap",value = {
//            @Result(id = true, property = "id", column = "id"),
//            @Result(property = "orderNum", column = "orderNum"),
//            @Result(property = "orderTime", column = "orderTime"),
//            @Result(property = "orderStatus", column = "orderStatus"),
//            @Result(property = "peopleCount", column = "peopleCount"),
//            @Result(property = "payType", column = "payType"),
//            @Result(property = "orderDesc", column = "orderDesc"),
//            @Result(property = "product", column = "productId", javaType = Product.class,one = @One(select = "com.ssm.dao.IProductDao.findById", fetchType = FetchType.EAGER)),
//            @Result(property = "user", column = "userId",javaType = Member.class,one = @One(select = "com.ssm.dao.IUserDao.findById", fetchType = FetchType.EAGER))
//    })
//    List<Order> findAll();

    @Select("select * from orders")
    List<Order> findAll();

    @Insert("insert into orders(orderTime,orderStatus,payType,userId,productId) values(#{orderTime},#{orderStatus},#{payType},#{userId},#{productId})")
    void save(Order order);

    @Select("select * from orders where id = #{id}")
    Order findById(String id);


    @Delete("delete from orders where id = #{id}")
    void deleteById(String id);

    @Update("update orders set deleted = 0 where id = #{id}")
    void userDeleteById(String id);

    @Select("select count(*) from orders")
    int findCnt();

    @Select("select count(*) from orders where orderStatus = 1")
    int findPayCnt();

    @Select("select count(*) from orders where orderStatus = 0")
    int findRefundCnt();

    @Select("select * from orders where orderStatus =0")
    List<Order> findRefund();

    @Select("select * from orders where userId = #{userId} and deleted = 1")
    List<Order> findByUserId(String id);

    @Update("update orders set orderTime=#{orderTime},orderStatus=#{orderStatus},payType=#{payType},userId=#{userId},productId=#{productId} where id=#{id}")
    void update(Order order);

    @Select("select * from orders where userId in (select id from users where username like '%${username}%')")
    List<Order> findOrdersByUsername(@Param(value = "username")String username);
}
