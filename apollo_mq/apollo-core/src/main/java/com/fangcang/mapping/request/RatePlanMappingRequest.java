package com.fangcang.mapping.request;

import lombok.Data;

import java.util.Date;

/**
 * Created by Owen on 2018/6/8.
 */
@Data
public class RatePlanMappingRequest {

    private String merchantCode;

    private Long hotelId;

    private Long roomTypeId;

    private String roomTypeName;

    private Long ratePlanId;

    private String ratePlanName;

    private String dltHotelId;

    private String dltRatePlanId;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}
