package com.fangcang.hotelinfo.domain;

import lombok.Data;

@Data
public class HotelMappingDO {

    private Integer id;

    /**
     * 原来酒店ID
     */
    private Long oldHotelId;

    /**
     * 新的酒店ID
     */
    private Long newHotelId;
}