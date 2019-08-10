package com.yunhe.ssm.service;

import com.yunhe.ssm.domain.Permission;

import java.util.List;

/**
 * @author James
 * @create 2019-08-11 1:18
 */
public interface IPermissionService {

    /**
     * 查询所有权限
     * @return
     * @throws Exception
     */
    List<Permission> findAll() throws Exception;

    /**
     * 添加权限
     * @param permission
     * @throws Exception
     */
    void save(Permission permission) throws Exception;
}
