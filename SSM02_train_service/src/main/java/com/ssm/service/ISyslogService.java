package com.ssm.service;

import com.ssm.domain.SysLog;

import java.io.UnsupportedEncodingException;
import java.util.List;

public interface ISyslogService {

    void save(SysLog sysLog);

    List<SysLog> findAll(int page, int size);

    int findCnt();

    void deleteSysLogById(String[] ids);

    List<SysLog> findSysLogLikeUserName(Integer page, Integer size, String username) throws UnsupportedEncodingException;
}
