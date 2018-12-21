package com.travel.channel.dao;

import com.travel.channel.entity.po.DltMapLogPO;
import com.travel.channel.entity.po.DltMapLogPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DltMapLogPOMapper {
    int countByExample(DltMapLogPOExample example);

    int deleteByExample(DltMapLogPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltMapLogPO record);

    int insertSelective(DltMapLogPO record);

    List<DltMapLogPO> selectByExample(DltMapLogPOExample example);

    DltMapLogPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltMapLogPO record, @Param("example") DltMapLogPOExample example);

    int updateByExample(@Param("record") DltMapLogPO record, @Param("example") DltMapLogPOExample example);

    int updateByPrimaryKeySelective(DltMapLogPO record);

    int updateByPrimaryKey(DltMapLogPO record);
}