package com.yunhe.ssm.service.impl;

import com.yunhe.ssm.dao.ISysLogDao;
import com.yunhe.ssm.domain.SysLog;
import com.yunhe.ssm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author James
 * @create 2019-08-14 16:37
 */
@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    /**
     * 保存日志实体
     * @param sysLog
     * @throws Exception
     */
    @Override
    public void save(SysLog sysLog) throws Exception {

        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll()  throws Exception {
        return sysLogDao.findAll();
    }
}
