package com.yunhe.ssm.dao;

import com.yunhe.ssm.domain.Role;
import com.yunhe.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author James
 *
 */
public interface IUserDao {

    /**
     * 根据用户名查询用户详细 包括角色
     * @param username
     * @return
     * @throws Exception
     */
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            /**
             * 根据用户Id查询角色表，得出用户的具体角色
             */
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yunhe.ssm.dao.IRoleDao.findRoleByUserId"))
    })
     UserInfo findByUsername(String username) throws Exception;

    /**
     * 查询所有的用户
     * @return
     */
    @Select("select * from users")
    List<UserInfo> findAll() throws  Exception;

    /**
     *  插入用户
     * @param userInfo
     */
    @Insert(" insert into users(email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo) throws Exception;

    /**
     *查询用户详细信息，包括角色、权限
     * @return
     */
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username", column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            /**
             * 根据用户Id查询角色表，得出用户的具体角色
             */
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.yunhe.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findByIdUser( String id) throws  Exception;


    /**
     * 查询其他角色
     * @param userId
     * @return
     */
    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId} )")
    List<Role> findOtherRoles(String userId);

    /**
     * 为指定用户添加角色
     * @param userId 用户ID
     * @param roleId 需要添加的角色ID
     */
    @Insert("insert into users_role (userId,roleId) values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
