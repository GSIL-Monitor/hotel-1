package com.fangcang.mapping.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class ElongRatePlanMappingResponse implements Serializable{
    private static final long serialVersionUID = 7718384325192044788L;
    private Integer hotelId;
    private Integer roomTypeId;
    private Integer ratePlanId;
    private String ratePlanName;
    private String elongRatePlanId;
    private String elongRatePlanName;
    private Integer mapStatus;
    private String modifiedTime;
}
