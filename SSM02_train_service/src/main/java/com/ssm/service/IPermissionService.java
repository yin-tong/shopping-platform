package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.domain.Permission;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IPermissionService {
    List<Permission> findAll(Integer page, Integer size);

    void save(Permission permission);

    List<Permission> findOtherPermission(String roleId);

    int findCnt();

    void delete(String[] ids);

    Permission findById(String id);

    void update(Permission permission);

    List<Permission> findPermissionsLikeName(Integer page, Integer size, String name) throws UnsupportedEncodingException;
}
