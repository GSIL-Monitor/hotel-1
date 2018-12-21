package com.fangcang.hotelinfo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class SimpleHotelDTO implements Serializable {
    private static final long serialVersionUID = -5985639611738428866L;

    /**
     * 酒店ID
     */
    private Long hotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
}
