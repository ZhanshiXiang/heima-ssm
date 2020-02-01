package com.yunhe.ssm.service;

import com.yunhe.ssm.domain.Permission;
import com.yunhe.ssm.domain.Role;

import java.util.List;

/**
 * @author James
 * @create 2019-08-10 16:33
 */
public interface IRoleService {
    /**
     * 查询所有角色
     * @return
     * @throws Exception
     */
    List<Role> findAll() throws  Exception;

    /**
     * 添加角色信息
     * @param role
     */
    void save(Role role) throws Exception;

    Role findById(String roleId) throws Exception;

    List<Permission> findOtherPermissions(String roleId);

    void addPermissionToRole(String roleId, String[] permissionIds);
}
