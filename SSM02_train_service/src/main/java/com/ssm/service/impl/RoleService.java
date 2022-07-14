package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.IPermissionDao;
import com.ssm.dao.IRoleDao;
import com.ssm.domain.Role;
import com.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    IRoleDao roleDao;

    @Autowired
    IPermissionDao permissionDao;

    @Override
    public List<Role> findAll(int page, int size) {
        PageHelper.startPage(page,size);
        return roleDao.findAll();
    }

    @Override
    public void save(Role role) {
        roleDao.save(role);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return roleDao.findOtherRoles(userId);
    }

    @Override
    public Role findById(String roleId) {
        Role role = roleDao.findById(roleId);
        role.setPermissions(permissionDao.findPermissionByRoleId(roleId));
        return role;
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds){
            roleDao. addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public void deleteRoleById(String[] roleIds) {
        for (String roleId : roleIds){
            roleDao.deleteUsersRoleByRoleId(roleId);
            roleDao.deleteRolePermissionByRoleId(roleId);
            roleDao.deleteRoleById(roleId);
        }
    }

    @Override
    public int findCnt() {
        return roleDao.findCnt();
    }

    @Override
    public List<Role> findRolesLikeRoleDesc(Integer page, Integer size, String desc) throws UnsupportedEncodingException {
        PageHelper.startPage(page,size);
        return roleDao.findRolesLikeRoleDesc(URLDecoder.decode(URLEncoder.encode(desc,"iso8859-1"),"utf-8"));
    }
}
