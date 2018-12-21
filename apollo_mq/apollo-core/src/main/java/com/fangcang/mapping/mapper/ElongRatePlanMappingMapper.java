package com.fangcang.mapping.mapper;

import com.fangcang.mapping.response.ElongRatePlanMappingResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ElongRatePlanMappingMapper {

    List<ElongRatePlanMappingResponse> queryMappingRatePlan(@Param("merchantCode")String merchantCode, @Param("list")List<Integer> hotelIdList);
}
