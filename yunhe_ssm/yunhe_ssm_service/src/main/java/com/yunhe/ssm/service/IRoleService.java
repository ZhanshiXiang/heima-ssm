package com.yunhe.ssm.service;

import com.yunhe.ssm.domain.Role;

import java.util.List;

/**
 * @author James
 * @create 2019-08-10 16:33
 */
public interface IRoleService {

    List<Role> findAll() throws  Exception;
}
