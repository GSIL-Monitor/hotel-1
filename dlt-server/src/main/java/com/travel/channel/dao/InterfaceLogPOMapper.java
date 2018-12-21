package com.travel.channel.dao;

import com.travel.channel.entity.po.InterfaceLogPO;
import com.travel.channel.entity.po.InterfaceLogPOExample;
import com.travel.channel.entity.po.InterfaceLogPOWithBLOBs;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface InterfaceLogPOMapper {
    int countByExample(InterfaceLogPOExample example);

    int deleteByExample(InterfaceLogPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(InterfaceLogPOWithBLOBs record);

    int insertSelective(InterfaceLogPOWithBLOBs record);

    List<InterfaceLogPOWithBLOBs> selectByExampleWithBLOBs(InterfaceLogPOExample example);

    List<InterfaceLogPO> selectByExample(InterfaceLogPOExample example);

    InterfaceLogPOWithBLOBs selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") InterfaceLogPOWithBLOBs record, @Param("example") InterfaceLogPOExample example);

    int updateByExampleWithBLOBs(@Param("record") InterfaceLogPOWithBLOBs record, @Param("example") InterfaceLogPOExample example);

    int updateByExample(@Param("record") InterfaceLogPO record, @Param("example") InterfaceLogPOExample example);

    int updateByPrimaryKeySelective(InterfaceLogPOWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(InterfaceLogPOWithBLOBs record);

    int updateByPrimaryKey(InterfaceLogPO record);
}