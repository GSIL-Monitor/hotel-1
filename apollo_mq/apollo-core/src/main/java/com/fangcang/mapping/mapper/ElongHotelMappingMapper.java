package com.fangcang.mapping.mapper;

import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.response.ElongHotelMappingResponse;

import java.util.List;

public interface ElongHotelMappingMapper {

    List<ElongHotelMappingResponse> queryMappingHotel(MappingQueryRequest request);
}
