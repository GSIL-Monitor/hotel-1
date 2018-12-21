package com.fangcang.mapping.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ElongRoomMappingResponse implements Serializable{
    private static final long serialVersionUID = 7718384325192044788L;
    private Integer hotelId;
    private Integer roomTypeId;
    private String roomTypeName;
    private String elonRoomTypeId;
    private String elongRoomTypeName;
    private String modifiedTime;
    private List<ElongRatePlanMappingResponse> ratePlanList;
}
