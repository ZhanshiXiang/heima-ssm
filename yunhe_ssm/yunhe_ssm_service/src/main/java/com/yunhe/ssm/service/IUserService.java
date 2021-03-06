package com.yunhe.ssm.service;

import com.yunhe.ssm.domain.Role;
import com.yunhe.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * 使用Spring Security进行用户认证
 */
public interface IUserService extends UserDetailsService {

    /**
     * 查询所有的用户
     *
     * @return
     */
    List<UserInfo> findAll() throws Exception;

    /**
     * 保存用户
     *
     * @param userInfo
     * @throws Exception
     */
    void save(UserInfo userInfo) throws Exception;

    /**
     * 根据用户Id查询用户的详情包括角色、权限信息
     *
     * @return
     */
    UserInfo findByIdUser(String id) throws Exception;

    /**
     * 查询其他角色
     * @param userid
     * @return
     * @throws Exception
     */
    List<Role> findOtherRoles(String userid) throws Exception;

    /**
     * 为指定用户添加角色
     * @param userId
     * @param roleIds
     */
    void addRoleToUser(String userId, String[] roleIds);
}


