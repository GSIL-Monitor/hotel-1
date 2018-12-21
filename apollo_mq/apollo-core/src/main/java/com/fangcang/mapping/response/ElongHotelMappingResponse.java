package com.fangcang.mapping.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ElongHotelMappingResponse implements Serializable{
    private static final long serialVersionUID = 7718384325192044788L;
    private Integer hotelId;
    private String hotelName;
    private String elongHotelId;
    private String elongHotelName;
    private String cityCode;
    private String cityName;
    private String modifiedTime;
    private List<ElongRoomMappingResponse> roomList;
}
