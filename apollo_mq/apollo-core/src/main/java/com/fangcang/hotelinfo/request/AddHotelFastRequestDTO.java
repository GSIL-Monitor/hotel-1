package com.fangcang.hotelinfo.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddHotelFastRequestDTO implements Serializable{

    private String cityCode;

    private String cityName;

    private String hotelName;

    private String roomTypeName;

    private String operator;

    private String merchantCode;
}
