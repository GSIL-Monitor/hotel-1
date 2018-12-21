package com.travel.channel.dao;

import com.travel.channel.dto.request.DltRoomMappingQueryDTO;
import com.travel.channel.dto.response.DltRoomMappingDTO;
import com.travel.channel.entity.po.DltMapRoomPO;
import com.travel.channel.entity.po.DltMapRoomPOExample;
import com.travel.hotel.product.entity.po.PricePlanPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DltMapRoomPOMapper {
    int countByExample(DltMapRoomPOExample example);

    int deleteByExample(DltMapRoomPOExample example);

    int deleteByPrimaryKey(Long id);

    int insert(DltMapRoomPO record);

    int insertSelective(DltMapRoomPO record);

    List<DltMapRoomPO> selectByExample(DltMapRoomPOExample example);

    DltMapRoomPO selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") DltMapRoomPO record, @Param("example") DltMapRoomPOExample example);

    int updateByExample(@Param("record") DltMapRoomPO record, @Param("example") DltMapRoomPOExample example);

    int updateByPrimaryKeySelective(DltMapRoomPO record);

    int updateByPrimaryKey(DltMapRoomPO record);

    List<DltRoomMappingDTO> selectRoomMappingList(DltRoomMappingQueryDTO dltRoomMappingQueryDTO);

    PricePlanPO queryPricePlan(Integer pricePlanId);
}