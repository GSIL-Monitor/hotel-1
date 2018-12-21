package com.fangcang.mapping.mapper;

import com.fangcang.common.MyMapper;
import com.fangcang.mapping.domain.HotelMappingDO;
import com.fangcang.mapping.request.MappingQueryRequest;
import com.fangcang.mapping.response.HotelMappingResponse;
import com.fangcang.mapping.response.RatePlanMappingResponse;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * Created by Owen on 2018/6/8.
 */
public interface HotelMappingMapper extends MyMapper<HotelMappingDO> {

    List<Long> queryMappingHotel(MappingQueryRequest mappingQueryRequest);

    List<Long> queryUnMappingHotel(MappingQueryRequest mappingQueryRequest);

    List<Long> queryAllHotel(MappingQueryRequest mappingQueryRequest);

    List<HotelMappingResponse> queryDltMappingHotelList(MappingQueryRequest mappingQueryRequest);

    List<RatePlanMappingResponse> queryDltRateMapping(@Param("merchantCode")String merchantCode, @Param("list")List<Long> hotelIdList);

}
