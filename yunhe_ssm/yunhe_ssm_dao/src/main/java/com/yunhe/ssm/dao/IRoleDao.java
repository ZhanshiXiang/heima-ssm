package com.yunhe.ssm.dao;

import com.yunhe.ssm.domain.Permission;
import com.yunhe.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {


    /** 用户详细信息查询
     * 根据用户id查询用户角色中间表，得出所有对应的角色id
     *  再根据角色id查询角色表，得出所具有的角色
     *  再根据角色id查询角色权限中间表，得出权限id
     *  最后根据权限id查询所有的权限。
     *  完成用户详细信息查询
     * @param userId
     * @return
     * @throws Exception
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{id}) ")
        @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id", javaType =java.util.List.class, many= @Many(select ="com.yunhe.ssm.dao.IPermissionDao.findPermissionByRoleId"))

        })
    List<Role> findRoleByUserId(String userId) throws Exception;


    /**
     * 查询所有的角色
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色
     * @param role
     */
    @Insert("insert into role(roleName,roleDesc) values(#{roleName},#{roleDesc})")
    void save(Role role);


    /**
     * 根据角色ID 查询出所有的角色
     * @param roleId
     * @return
     */
    @Select("select * from role where id =#{roleId}")
    Role findById(String roleId);

    /**
     * 先根据角色id查询出角色权限中间表中的权限id
     *  再根据查询得出的权限id，not in排除当前查询的所有拥有的权限，
     *      剩下的就是可以添加的权限。
     * @param roleId
     * @return
     */
    @Select("select * from permission where id not in(select permissionId  from role_permission where id=#{roleId})")
    List<Permission> findOtherPermissions(String roleId);

    /**
     * 为角色添加权限
     * @param permissionId
     * @param roleId
     */
    @Insert("insert into role_permission (permissionId,roleId) values (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}
