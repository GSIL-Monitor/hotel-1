package com.travel.hotel.order.dao;

import com.travel.hotel.order.entity.po.OrderQuotaLogPO;
import com.travel.hotel.order.entity.po.OrderQuotaLogPOExample;
import com.travel.hotel.order.entity.po.UsedQuotaPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderQuotaLogPOMapper {
    int countByExample(OrderQuotaLogPOExample example);

    int deleteByExample(OrderQuotaLogPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OrderQuotaLogPO record);

    int insertSelective(OrderQuotaLogPO record);

    List<OrderQuotaLogPO> selectByExampleWithBLOBs(OrderQuotaLogPOExample example);

    List<OrderQuotaLogPO> selectByExample(OrderQuotaLogPOExample example);

    OrderQuotaLogPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OrderQuotaLogPO record, @Param("example") OrderQuotaLogPOExample example);

    int updateByExampleWithBLOBs(@Param("record") OrderQuotaLogPO record, @Param("example") OrderQuotaLogPOExample example);

    int updateByExample(@Param("record") OrderQuotaLogPO record, @Param("example") OrderQuotaLogPOExample example);

    int updateByPrimaryKeySelective(OrderQuotaLogPO record);

    int updateByPrimaryKeyWithBLOBs(OrderQuotaLogPO record);

    int updateByPrimaryKey(OrderQuotaLogPO record);

    /**
     * 查询已用配额的订单
     * @param record
     * @return
     */
    List<UsedQuotaPO> queryUsedQuotaOrderList(OrderQuotaLogPO record);

}