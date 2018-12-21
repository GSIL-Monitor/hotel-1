package com.fangcang.ebk.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class SupplyHotelDTO implements Serializable {
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
