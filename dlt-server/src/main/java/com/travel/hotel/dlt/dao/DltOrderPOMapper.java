package com.travel.hotel.dlt.dao;

import com.travel.hotel.dlt.entity.po.DltOrderPO;
import com.travel.hotel.dlt.entity.po.DltOrderPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DltOrderPOMapper {
    int countByExample(DltOrderPOExample example);

    int deleteByExample(DltOrderPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltOrderPO record);

    int insertSelective(DltOrderPO record);

    int insertBatch(List<DltOrderPO> recordList);

    List<DltOrderPO> selectByExample(DltOrderPOExample example);

    DltOrderPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltOrderPO record, @Param("example") DltOrderPOExample example);

    int updateByExample(@Param("record") DltOrderPO record, @Param("example") DltOrderPOExample example);

    int updateByPrimaryKeySelective(DltOrderPO record);

    int updateByPrimaryKey(DltOrderPO record);
}