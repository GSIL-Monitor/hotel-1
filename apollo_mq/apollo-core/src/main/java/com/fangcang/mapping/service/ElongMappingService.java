package com.fangcang.mapping.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.response.ElongHotelMappingResponse;

public interface ElongMappingService {

    /**
     * 查询艺龙映射情况
     * @param mappingQueryRequest
     * @return
     */
    ResponseDTO<PaginationSupportDTO<ElongHotelMappingResponse>> queryElongMapping(MappingQueryRequest mappingQueryRequest);
}
