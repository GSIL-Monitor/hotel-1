package com.travel.hotel.order.dao;

import com.travel.hotel.order.entity.po.OrderOperateLogPO;
import com.travel.hotel.order.entity.po.OrderOperateLogPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderOperateLogPOMapper {
    int countByExample(OrderOperateLogPOExample example);

    int deleteByExample(OrderOperateLogPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderOperateLogPO record);

    int insertSelective(OrderOperateLogPO record);

    List<OrderOperateLogPO> selectByExample(OrderOperateLogPOExample example);

    OrderOperateLogPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderOperateLogPO record, @Param("example") OrderOperateLogPOExample example);

    int updateByExample(@Param("record") OrderOperateLogPO record, @Param("example") OrderOperateLogPOExample example);

    int updateByPrimaryKeySelective(OrderOperateLogPO record);

    int updateByPrimaryKey(OrderOperateLogPO record);
}