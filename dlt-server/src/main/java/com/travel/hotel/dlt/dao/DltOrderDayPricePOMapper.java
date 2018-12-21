package com.travel.hotel.dlt.dao;

import com.travel.hotel.dlt.entity.po.DltOrderCancelRulesPO;
import com.travel.hotel.dlt.entity.po.DltOrderDayPricePO;
import com.travel.hotel.dlt.entity.po.DltOrderDayPricePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DltOrderDayPricePOMapper {
    int countByExample(DltOrderDayPricePOExample example);

    int deleteByExample(DltOrderDayPricePOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltOrderDayPricePO record);

    int insertSelective(DltOrderDayPricePO record);

    void insertBatch(List<DltOrderDayPricePO> dltOrderDayPricePOList);

    List<DltOrderDayPricePO> selectByExample(DltOrderDayPricePOExample example);

    DltOrderDayPricePO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltOrderDayPricePO record, @Param("example") DltOrderDayPricePOExample example);

    int updateByExample(@Param("record") DltOrderDayPricePO record, @Param("example") DltOrderDayPricePOExample example);

    int updateByPrimaryKeySelective(DltOrderDayPricePO record);

    int updateByPrimaryKey(DltOrderDayPricePO record);
}