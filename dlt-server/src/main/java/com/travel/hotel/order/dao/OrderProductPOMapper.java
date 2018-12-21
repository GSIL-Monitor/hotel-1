package com.travel.hotel.order.dao;

import com.travel.common.dto.order.OrderProductDTO;
import com.travel.hotel.order.entity.po.OrderProductPO;
import com.travel.hotel.order.entity.po.OrderProductPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderProductPOMapper {
    int countByExample(OrderProductPOExample example);

    int deleteByExample(OrderProductPOExample example);

    int deleteByPrimaryKey(Long opid);

    int insert(OrderProductPO record);

    int insertSelective(OrderProductPO record);

    List<OrderProductPO> selectByExample(OrderProductPOExample example);

    int insertBatch(List<OrderProductPO> recordList);

    OrderProductPO selectByPrimaryKey(Long opid);

    int updateByExampleSelective(@Param("record") OrderProductPO record, @Param("example") OrderProductPOExample example);

    int updateByExample(@Param("record") OrderProductPO record, @Param("example") OrderProductPOExample example);

    List<OrderProductPO> queryList(OrderProductDTO orderProductDTO);

    int updateByPrimaryKeySelective(OrderProductPO record);

    int updateByPrimaryKey(OrderProductPO record);
}