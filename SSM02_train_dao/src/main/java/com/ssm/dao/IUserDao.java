package com.ssm.dao;

import com.ssm.domain.Role;
import com.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;


@Component
public interface IUserDao {
    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select({"select * from users where username=#{username}"})
    @Results(id = "userMap",value={
            @Result(id=true,property = "id",column = "id"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByUsername(String username);

    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from users")
    List<UserInfo> findAll();

    @Insert("insert into users(email,username,password,phoneNum,status) values(#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo);

    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select * from users where id = #{id}")
    @ResultMap("userMap")
    UserInfo findById(String id);

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

    @Delete("delete from users where id=#{id}")
    void deleteUserById(@Param("id") String id);

    @Delete("delete from users_role where userid = #{id}")
    void deleteUserRoleByUserId(@Param("id") String id);

    @Options(flushCache = Options.FlushCachePolicy.TRUE)
    @Select("select count(*) from users")
    int findCnt();

    @Update("update users set email=#{email},username=#{username},password=#{password},phoneNum=#{phoneNum},status=#{status} where id=#{id}")
    void update(UserInfo userInfo);

    @Select("select * from users where username like '%${name}%'")
    List<UserInfo> fuzzyQueryByUserName(@Param("name")String name);

    @Update("update users set money=money+#{moneyF} where username = #{username}")
    void addMoney(@Param("username")String username, @Param("moneyF")float moneyF);

    @Update("update users set money=money-#{moneyF} where username = #{username}")
    void reduceMoney(@Param("username")String username, @Param("moneyF")float moneyF);
}
