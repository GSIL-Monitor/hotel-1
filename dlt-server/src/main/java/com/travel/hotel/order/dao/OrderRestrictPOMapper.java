package com.travel.hotel.order.dao;

import com.travel.hotel.order.entity.po.OrderRestrictPO;
import com.travel.hotel.order.entity.po.OrderRestrictPOExample;
import java.util.List;

public interface OrderRestrictPOMapper {
    int deleteByPrimaryKey(Long restrictId);

    int insert(OrderRestrictPO record);

    int insertSelective(OrderRestrictPO record);

    int insertBatch(List<OrderRestrictPO> orderRestrictPOList);

    List<OrderRestrictPO> selectByExample(OrderRestrictPOExample example);

    OrderRestrictPO selectByPrimaryKey(Long restrictId);

    int updateByPrimaryKeySelective(OrderRestrictPO record);

    int updateByPrimaryKey(OrderRestrictPO record);
}