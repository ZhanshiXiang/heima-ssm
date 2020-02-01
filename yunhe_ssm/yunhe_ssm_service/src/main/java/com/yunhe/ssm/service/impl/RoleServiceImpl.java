package com.yunhe.ssm.service.impl;

import com.yunhe.ssm.dao.IRoleDao;
import com.yunhe.ssm.domain.Permission;
import com.yunhe.ssm.domain.Role;
import com.yunhe.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author James
 * @create 2019-08-10 16:34
 */
@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;


    /**
     * 查询所有的角色
     * @return
     * @throws Exception
     */
    @Override
    public List<Role> findAll() throws Exception {

        return roleDao.findAll();
    }

    @Override
    public void save(Role role) throws Exception{
        roleDao.save(role);

    }

    @Override
    public Role findById(String roleId)  throws Exception{
        return roleDao.findById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissions(String roleId) {
        return roleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
       for(String permissionId:permissionIds){
           roleDao.addPermissionToRole(roleId,permissionId);
       }
    }
}
