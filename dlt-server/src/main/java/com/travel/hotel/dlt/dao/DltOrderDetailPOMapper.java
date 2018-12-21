package com.travel.hotel.dlt.dao;

import com.travel.hotel.dlt.entity.po.DltOrderDetailPO;
import com.travel.hotel.dlt.entity.po.DltOrderDetailPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DltOrderDetailPOMapper {
    int countByExample(DltOrderDetailPOExample example);

    int deleteByExample(DltOrderDetailPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltOrderDetailPO record);

    int insertSelective(DltOrderDetailPO record);

    List<DltOrderDetailPO> selectByExample(DltOrderDetailPOExample example);

    DltOrderDetailPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltOrderDetailPO record, @Param("example") DltOrderDetailPOExample example);

    int updateByExample(@Param("record") DltOrderDetailPO record, @Param("example") DltOrderDetailPOExample example);

    int updateByPrimaryKeySelective(DltOrderDetailPO record);

    int updateByPrimaryKey(DltOrderDetailPO record);
}