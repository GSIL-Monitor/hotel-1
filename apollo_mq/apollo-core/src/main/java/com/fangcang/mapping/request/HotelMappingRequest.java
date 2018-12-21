package com.fangcang.mapping.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
public class HotelMappingRequest {

    private String merchantCode;

    private Long hotelId;

    private String hotelName;

    private String dltHotelId;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

    private String cityCode;


    private Integer mappingStatus;

}
