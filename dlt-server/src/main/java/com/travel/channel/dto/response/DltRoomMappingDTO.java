package com.travel.channel.dto.response;

import lombok.Data;

/**
 *   2018/5/2.
 */
@Data
public class DltRoomMappingDTO {

    private Long hotelId;
    private String hotelName;
    private Long roomId;
    private String roomName;
    private Long pricePlanId;
    private String pricePlanName;
    private Integer isActive;
    private Long dltRoomId;

}
