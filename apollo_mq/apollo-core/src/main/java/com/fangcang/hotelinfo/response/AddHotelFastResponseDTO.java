package com.fangcang.hotelinfo.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class AddHotelFastResponseDTO implements Serializable{

    private Integer hotelId;

    private String hotelName;

    private Integer roomTypeId;

    private String roomTypeName;
}
