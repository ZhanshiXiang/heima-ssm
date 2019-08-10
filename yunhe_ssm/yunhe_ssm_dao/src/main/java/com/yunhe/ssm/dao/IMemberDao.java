package com.yunhe.ssm.dao;

import com.yunhe.ssm.domain.Member;
import org.apache.ibatis.annotations.Select;

public interface IMemberDao {

    @Select("select * from member where id=#{id}")
     Member findById(String id) throws Exception;
}