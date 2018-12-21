package com.travel.hotel.order.dao;

import com.travel.hotel.order.entity.po.OrderDayPricePO;
import com.travel.hotel.order.entity.po.OrderDayPricePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDayPricePOMapper {
    int countByExample(OrderDayPricePOExample example);

    int deleteByExample(OrderDayPricePOExample example);

    int deleteByPrimaryKey(Long dayPriceId);

    int insert(OrderDayPricePO record);

    int insertSelective(OrderDayPricePO record);

    int insertBatch(List<OrderDayPricePO> recordList);

    List<OrderDayPricePO> selectByExample(OrderDayPricePOExample example);

    OrderDayPricePO selectByPrimaryKey(Long dayPriceId);
    
    List<OrderDayPricePO> selectByPrimaryKeyList(List<Long> dayPriceIdList);

    int updateByExampleSelective(@Param("record") OrderDayPricePO record, @Param("example") OrderDayPricePOExample example);

    int updateByExample(@Param("record") OrderDayPricePO record, @Param("example") OrderDayPricePOExample example);

    int updateByPrimaryKeySelective(OrderDayPricePO record);

    int updateByPrimaryKey(OrderDayPricePO record);
}