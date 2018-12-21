package com.fangcang.mapping.mapper;

import com.fangcang.mapping.response.ElongRoomMappingResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElongRoomMappingMapper {

    List<ElongRoomMappingResponse> queryMappingRoom(@Param("merchantCode")String merchantCode, @Param("list")List<Integer> hotelIdList);
}
