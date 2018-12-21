package com.travel.hotel.dlt.request.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 *   2018/4/8.
 */
@Data
public class RoomStatusModel {

    private Integer saleStatus = 0;
    private String channel;

}
