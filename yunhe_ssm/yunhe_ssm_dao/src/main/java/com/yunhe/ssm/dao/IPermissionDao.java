package com.yunhe.ssm.dao;

import com.yunhe.ssm.domain.Permission;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author James
 * @create 2019-08-10 1:34
 */
public interface IPermissionDao {

    /**
     * 根据角色Id查询权限信息
     * @param id
     * @return
     * @throws Exception
     */
    @Select("select * from permission where id in(select permissionId from role_permission where roleId=#{id})")
    List<Permission> findPermissionByRoleId(String id) throws Exception;
}
