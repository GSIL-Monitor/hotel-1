package com.travel.hotel.dlt.dao;

import com.travel.hotel.dlt.entity.po.DltOrderCancelRulesPO;
import com.travel.hotel.dlt.entity.po.DltOrderCancelRulesPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DltOrderCancelRulesPOMapper {
    int countByExample(DltOrderCancelRulesPOExample example);

    int deleteByExample(DltOrderCancelRulesPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltOrderCancelRulesPO record);

    int insertSelective(DltOrderCancelRulesPO record);

    void insertBatch(List<DltOrderCancelRulesPO> dltOrderCancelRulesPOList);

    List<DltOrderCancelRulesPO> selectByExample(DltOrderCancelRulesPOExample example);

    DltOrderCancelRulesPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltOrderCancelRulesPO record, @Param("example") DltOrderCancelRulesPOExample example);

    int updateByExample(@Param("record") DltOrderCancelRulesPO record, @Param("example") DltOrderCancelRulesPOExample example);

    int updateByPrimaryKeySelective(DltOrderCancelRulesPO record);

    int updateByPrimaryKey(DltOrderCancelRulesPO record);
}