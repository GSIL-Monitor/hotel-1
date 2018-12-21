package com.fangcang.mapping.response;

import lombok.Data;

import java.util.List;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
public class HotelMappingResponse {

    private Long hotelId;

    private String hotelName;

    private String cityCode;

    private String cityName;

    private String dltHotelId;

    private String merchantCode;

    private List<RoomMappingResponse> roomTypeList;

    private List<RatePlanMappingResponse> ratePlanList;

}
