package com.yunhe.ssm.service.impl;

import com.yunhe.ssm.dao.IPermissionDao;
import com.yunhe.ssm.domain.Permission;
import com.yunhe.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author James
 * @create 2019-08-11 1:19
 */
@Service
public class PermissionServiceImpl implements IPermissionService {


    @Autowired
    private IPermissionDao permissionDao;


    @Override
    public List<Permission> findAll() throws Exception {

        return  permissionDao.findAll();
    }

    @Override
    public void save(Permission permission) throws Exception {

         permissionDao.save(permission);
    }
}
