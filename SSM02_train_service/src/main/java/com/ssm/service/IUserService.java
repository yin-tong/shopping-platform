package com.ssm.service;

import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUserService extends UserDetailsService {

    List<UserInfo> findAll(int page, int size);

    void save(UserInfo userInfo);

    UserInfo findById(String id);

    UserInfo findByUserName(String username);

    void addRoleToUser(String userId, String[] roleIds);

    void deleteUser(String[] id);

    void update(UserInfo userInfo);

    int findCnt();

    void open(String[] ids);

    void close(String[] ids);

    List<UserInfo> findUsersLikeUsername(Integer page, Integer size, String username) throws UnsupportedEncodingException;

    void addMoney(String username, String money);

    void reduceMoney(String username, String money);
}
