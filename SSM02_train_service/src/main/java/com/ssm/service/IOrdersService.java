package com.ssm.service;

import com.ssm.domain.Order;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IOrdersService {
    /**
     * 查询所有订单
     */
    List<Order> findAll(int page, int size);

    Order findById(String id);

    void save(Order order);

    void deleteById(String id);

    int findCnt();

    int findPayCnt();

    int findRefundCnt();

    List<Order> findRefund(Integer page, Integer size);

    List<Order> findByUserId(String id, Integer page, Integer size);

    void update(Order order);

    void userDeleteById(String id);

    List<Order> findOrdersByUsername(Integer page, Integer size, String username) throws UnsupportedEncodingException;
}
