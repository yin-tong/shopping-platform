package com.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.ISyslogDao;
import com.ssm.domain.SysLog;
import com.ssm.service.ISyslogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

@Service
@Transactional
public class SyslogService implements ISyslogService {

    @Autowired
    ISyslogDao syslogDao;

    @Override
    public void save(SysLog sysLog) {
        syslogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(int page, int size) {
        //从第一页开始，每页查询size个
        PageHelper.startPage(page,size);
        return syslogDao.findAll();
    }

    @Override
    public int findCnt() {
        return syslogDao.findCnt();
    }

    @Override
    public void deleteSysLogById(String[] ids) {
        for (String id : ids){
            syslogDao.deleteSysLogById(id);
        }
    }

    @Override
    public List<SysLog> findSysLogLikeUserName(Integer page, Integer size, String username) throws UnsupportedEncodingException {
        PageHelper.startPage(page,size);
        return syslogDao.findSysLogLikeUserName(URLDecoder.decode(URLEncoder.encode(username,"iso8859-1"),"utf-8"));
    }
}
