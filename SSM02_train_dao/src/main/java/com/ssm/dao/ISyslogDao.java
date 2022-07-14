package com.ssm.dao;

import com.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ISyslogDao {

    @Insert("insert into syslog(visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog);

    @Select("select * from syslog order by visittime desc")
    List<SysLog> findAll();

    @Select("select count(*) from syslog")
    int findCnt();

    @Delete("delete from syslog where id = #{id}")
    void deleteSysLogById(String Id);

    @Select("select * from syslog where username like '%${username}%'")
    List<SysLog> findSysLogLikeUserName(@Param("username") String username);
}
