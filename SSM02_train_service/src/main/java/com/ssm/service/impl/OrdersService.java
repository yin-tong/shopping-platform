package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IOrdersDao;
import com.ssm.dao.IProductDao;
import com.ssm.dao.IUserDao;
import com.ssm.domain.Order;
import com.ssm.domain.UserInfo;
import com.ssm.service.IOrdersService;
import com.ssm.service.IProductService;
import com.ssm.service.IUserService;
import com.ssm.utils.MoneyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.LinkedList;
import java.util.List;

@Service
@Transactional
public class OrdersService implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Order> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return ordersDao.findAll();
    }

    @Override
    public Order findById(String id) {
        return ordersDao.findById(id);
    }

    @Override
    public void save(Order order) {
        ordersDao.save(order);
    }

    @Override
    public void deleteById(String id) {
        ordersDao.deleteById(id);
    }

    @Override
    public int findCnt() {
        return ordersDao.findCnt();
    }

    @Override
    public int findPayCnt() {
        return ordersDao.findPayCnt();
    }

    @Override
    public int findRefundCnt() {
        return ordersDao.findRefundCnt();
    }

    @Override
    public List<Order> findRefund(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return ordersDao.findRefund();
    }

    @Override
    public List<Order> findByUserId(String id, Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return ordersDao.findByUserId(id);
    }

    @Override
    public void update(Order order) {
        ordersDao.update(order);
    }

    @Override
    public void userDeleteById(String id) {
        ordersDao.userDeleteById(id);
    }

    @Override
    public List<Order> findOrdersByUsername(Integer page, Integer size, String username) throws UnsupportedEncodingException {
        return ordersDao.findOrdersByUsername(URLDecoder.decode(URLEncoder.encode(username,"iso8859-1"),"utf-8"));
    }
}
