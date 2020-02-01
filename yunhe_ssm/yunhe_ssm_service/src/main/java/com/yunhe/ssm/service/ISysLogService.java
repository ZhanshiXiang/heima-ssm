package com.yunhe.ssm.service;

import com.yunhe.ssm.domain.SysLog;

import java.util.List;

/**
 * @author James
 * @create 2019-08-14 16:37
 */
public interface ISysLogService {

    /**
     * 保存日志实体
     * @param sysLog
     * @throws Exception
     */
    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll() throws Exception;
}
