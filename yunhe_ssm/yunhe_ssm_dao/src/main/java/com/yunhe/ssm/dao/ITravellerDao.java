package com.yunhe.ssm.dao;

import com.yunhe.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author James
 *
 *
 */
public interface ITravellerDao {

    /**
     * 根据订单页面中的ordersId订单ID查询订单旅客中间表中的旅客Id
     *   然后再根据上一步查询得到旅客Id查询旅客表中的旅客详细信息
     * @param ordersId
     * @return
     * @throws Exception
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
     List<Traveller> findByOrdersId(String ordersId) throws Exception;
}
