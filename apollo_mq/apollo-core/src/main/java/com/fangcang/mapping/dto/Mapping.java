package com.fangcang.mapping.dto;

import lombok.Data;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
public class Mapping {

    private Long hotelId;

    private String hotelName;

    private String cityCode;

    private String cityName;

    private String dltHotelId;

    private String merchantCode;

    private Long roomTypeId;

    private String roomTypeName;

    private Long ratePlanId;

    private String ratePlanName;

    private String dltRatePlanId;

}
