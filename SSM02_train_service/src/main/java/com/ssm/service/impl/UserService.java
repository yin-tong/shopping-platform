package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IPermissionDao;
import com.ssm.dao.IUserDao;
import com.ssm.domain.Permission;
import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import com.ssm.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    IUserDao userDao;

    @Autowired
    IPermissionDao permissionDao;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;

        userInfo = userDao.findByUsername(username);
        //将自己的用户对象封装成UserDetails
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role:roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    public List<UserInfo> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return userDao.findAll();
    }

    @Override
    public void save(UserInfo userInfo) {
        System.out.println("==============="+userInfo);
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    @Override
    public UserInfo findById(String id) {
        UserInfo userInfo = userDao.findById(id);
        List<Role> roles = userInfo.getRoles();
        for (Role role:roles){
            List<Permission> permissions = permissionDao.findPermissionByRoleId(role.getId());
            role.setPermissions(permissions);
        }
        userInfo.setRoles(roles);
        return userInfo;
    }

    @Override
    public UserInfo findByUserName(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId:roleIds){
            userDao.addRoleToUser(userId, roleId);
        }
    }

    @Override
    public void deleteUser(String[] ids) {
        for (String id : ids){
            userDao.deleteUserRoleByUserId(id);
            userDao.deleteUserById(id);
        }
    }

    @Override
    public void update(UserInfo userInfo) {
        userDao.update(userInfo);
    }

    @Override
    public int findCnt() {
        return userDao.findCnt();
    }

    @Override
    public void open(String[] ids) {
        for (String id : ids){
            UserInfo userInfo = userDao.findById(id);
            userInfo.setStatus(1);
            userDao.update(userInfo);
        }
    }

    @Override
    public void close(String[] ids) {
        for (String id : ids){
            UserInfo userInfo = userDao.findById(id);
            userInfo.setStatus(0);
            userDao.update(userInfo);
        }
    }

    @Override
    public List<UserInfo> findUsersLikeUsername(Integer page, Integer size, String username) throws UnsupportedEncodingException {
        PageHelper.startPage(page,size);
        return userDao.fuzzyQueryByUserName(URLDecoder.decode(URLEncoder.encode(username,"iso8859-1"),"utf-8"));
    }

    @Override
    public void addMoney(String username, String money) {
        float moneyF = Float.parseFloat(money);
        userDao.addMoney(username,moneyF);
    }

    @Override
    public void reduceMoney(String username, String money) {
        float moneyF = Float.parseFloat(money);
        userDao.reduceMoney(username,moneyF);
    }


}
