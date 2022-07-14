package com.ssm.dao;

import com.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (select permissionId from role_permission where roleId = #{id})")
    List<Permission> findPermissionByRoleId(String id);

    @Select("select * from permission order by permissionName")
    List<Permission> findAll();

    @Insert("insert into permission(permissionName,url) values(#{permissionName},#{url})")
    void save(Permission permission);


    @Select("select * from permission where id not in(select permissionId from role_permission where roleId =#{roleId})")
    List<Permission> findOtherPermissionByRoleId(String roleId);

    @Select("select count(*) from permission")
    int findCnt();

    @Select("select * from permission where url = #{url}")
    Permission findByUrl(String url);

    @Delete("delete from role_permission where permissionId = #{id}")
    void deleteRolePermission(String id);

    @Delete("delete from permission where id = #{id}")
    void delete(String id);

    @Select("select * from permission where id = #{id}")
    Permission findById(String id);

    @Update("update permission set permissionName = #{permissionName},url =#{url} where id = #{id} ")
    void update(Permission permission);

    @Select("select * from permission where permissionName like '%${name}%' order by permissionName")
    List<Permission> findPermissionsLikeName(@Param(value = "name")String name);
}
