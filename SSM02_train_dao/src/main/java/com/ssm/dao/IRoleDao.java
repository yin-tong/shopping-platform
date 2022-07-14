package com.ssm.dao;


import com.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface IRoleDao {

    /**
     * 根据用户id查询所有对应的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(id = "roleMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "permission",column = "id",javaType = java.util.List.class,many = @Many(select = "com.ssm.dao.IPermissionDao.findPermissionByRoleId",fetchType = FetchType.LAZY))
    })
    List<Role> findRoleByUserId(String userId);

    @Select("select * from role")
    List<Role> findAll();

    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);

    @Select("select * from role where id not in (select roleId from users_role where userId=#{userId})")
    @ResultMap("roleMap")
    List<Role> findOtherRoles(String userId);

    @Select("select * from role where id = #{roleId}")
    @ResultMap("roleMap")
    Role findById(String roleId);

    @Insert("insert into role_permission(permissionId,roleId) values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId,@Param("permissionId")  String permissionId);

    @Delete("delete from role where id = #{roleId}")
    @ResultMap("roleMap")
    void deleteRoleById(String roleId);

    @Delete("delete from users_role where roleid = #{roleId}")
    void deleteUsersRoleByRoleId(String roleId);

    @Delete("delete from role_permission where roleid =#{roleId}")
    void deleteRolePermissionByRoleId(String roleId);

    @Select("select count(*) from role")
    int findCnt();

    @Select("select * from role where roleDesc like '%${desc}%'")
    List<Role> findRolesLikeRoleDesc(@Param("desc")String desc);

    @Select("select * from role where id in (select roleId from role_permission where permissionId=#{permissionId})")
    List<Role> findRoleBypermissionId(String permissionId);
}
