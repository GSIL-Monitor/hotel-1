package com.travel.hotel.product.dao;

import com.travel.hotel.product.entity.po.DatePO;
import com.travel.hotel.product.entity.po.DatePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DatePOMapper {
    int countByExample(DatePOExample example);

    int deleteByExample(DatePOExample example);

    int deleteByPrimaryKey(Long dateid);

    int insert(DatePO record);

    int insertBatch(List<DatePO> recordList);

    int insertSelective(DatePO record);

    List<DatePO> selectByExample(DatePOExample example);

    DatePO selectByPrimaryKey(Long dateid);

    int updateByExampleSelective(@Param("record") DatePO record, @Param("example") DatePOExample example);

    int updateByExample(@Param("record") DatePO record, @Param("example") DatePOExample example);

    int updateByPrimaryKeySelective(DatePO record);

    int updateByPrimaryKey(DatePO record);
}