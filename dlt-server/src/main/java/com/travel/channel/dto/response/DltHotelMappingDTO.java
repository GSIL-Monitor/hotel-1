package com.travel.channel.dto.response;

import lombok.Data;

/**
 *   2018/5/2.
 */
@Data
public class DltHotelMappingDTO {

    private Long hotelId;
    private String hotelName;
    private String cityCode;
    private String cityName;
    private String star;
    private String starName;
    private String tel;
    private Long dltHotelId;

}
