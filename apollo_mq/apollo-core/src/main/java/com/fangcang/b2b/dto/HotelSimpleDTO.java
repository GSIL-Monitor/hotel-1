package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/8/2.
 */
@Data
public class HotelSimpleDTO implements Serializable {
    private static final long serialVersionUID = 4801525592172956361L;

    /**
     * 酒店ID
     */
    private Long hotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
}
