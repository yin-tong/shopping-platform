package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IPermissionDao;
import com.ssm.domain.Permission;
import com.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
public class PermissionService implements IPermissionService {

    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll(Integer page, Integer size) {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
         permissionDao.save(permission);
    }

    @Override
    public List<Permission> findOtherPermission(String roleId) {
        return permissionDao.findOtherPermissionByRoleId(roleId);
    }

    @Override
    public int findCnt() {
        return permissionDao.findCnt();
    }

    @Override
    public void delete(String[] ids) {
        for (int i = 0; i < ids.length; i++) {
            permissionDao.deleteRolePermission(ids[i]);
            permissionDao.delete(ids[i]);
        }
    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void update(Permission permission) {
        permissionDao.update(permission);
    }

    @Override
    public List<Permission> findPermissionsLikeName(Integer page, Integer size, String name) throws UnsupportedEncodingException {
        PageHelper.startPage(page,size);
        return permissionDao.findPermissionsLikeName(URLDecoder.decode(URLEncoder.encode(name,"iso8859-1"),"utf-8"));
    }
}
