package com.travel.channel.dao;

import com.travel.channel.dto.request.DltHotelMappingQueryDTO;
import com.travel.channel.dto.response.DltHotelMappingDTO;
import com.travel.channel.entity.po.DltMapHotelPO;
import com.travel.channel.entity.po.DltMapHotelPOExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DltMapHotelPOMapper {
    int countByExample(DltMapHotelPOExample example);

    int deleteByExample(DltMapHotelPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltMapHotelPO record);

    int insertSelective(DltMapHotelPO record);

    List<DltMapHotelPO> selectByExample(DltMapHotelPOExample example);

    DltMapHotelPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltMapHotelPO record, @Param("example") DltMapHotelPOExample example);

    int updateByExample(@Param("record") DltMapHotelPO record, @Param("example") DltMapHotelPOExample example);

    int updateByPrimaryKeySelective(DltMapHotelPO record);

    int updateByPrimaryKey(DltMapHotelPO record);

    List<DltHotelMappingDTO> selectHotelMappingList(DltHotelMappingQueryDTO dltHotelMappingQueryDTO);
}