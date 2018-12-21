package com.travel.channel.dao;

import com.travel.channel.dto.request.QueryHotelProductDetailRequest;
import com.travel.channel.dto.response.QueryHotelProductDetailResponse;
import com.travel.hotel.dlt.response.dto.HotelRestrictDTO;

import java.util.List;

public interface ProductMapper {

    List<QueryHotelProductDetailResponse> selectProductDetail(QueryHotelProductDetailRequest queryHotelProductDetailRequest);

    HotelRestrictDTO queryHotelRestrict(Long ratePlanId);
}