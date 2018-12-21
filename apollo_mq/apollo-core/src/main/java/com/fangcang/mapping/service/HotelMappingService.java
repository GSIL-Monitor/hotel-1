package com.fangcang.mapping.service;

import com.fangcang.common.PaginationSupportDTO;
import com.fangcang.common.ResponseDTO;
import com.fangcang.mapping.request.HotelMappingRequest;
import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.request.RatePlanMappingRequest;
import com.fangcang.mapping.response.HotelMappingResponse;

import java.util.List;

/**
 * Created by Owen on 2018/6/8.
 */
public interface HotelMappingService {

    ResponseDTO<PaginationSupportDTO<List<HotelMappingResponse>>> queryMappingNew(MappingQueryRequest mappingQueryRequest);

    /**
     * 查询映射关系
     * @param mappingQueryRequest
     * @return
     */
    public ResponseDTO queryMapping(MappingQueryRequest mappingQueryRequest);

    /**
     * 新增或者修改酒店映射
     * @param hotelMappingRequest
     * @return
     */
    public ResponseDTO saveOrUpdateHotelMapping(HotelMappingRequest hotelMappingRequest);

    /**
     * 新增或者修改房型映射
     * @param ratePlanMappingRequest
     * @return
     */
    public ResponseDTO saveOrUpdateRatePlanMapping(RatePlanMappingRequest ratePlanMappingRequest);
}
