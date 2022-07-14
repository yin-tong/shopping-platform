package com.ssm.service;

import com.ssm.domain.Role;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IRoleService {


    List<Role> findAll(int page, int size);

    void save(Role role);

    List<Role> findOtherRoles(String userId);

    Role findById(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);

    void deleteRoleById(String[] roleIds);

    int findCnt();

    List<Role> findRolesLikeRoleDesc(Integer page, Integer size, String desc) throws UnsupportedEncodingException;
}
