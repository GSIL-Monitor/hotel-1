package com.fangcang.hotelinfo.request;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/8.
 */
@Data
public class MeetingRoomListQueryDTO implements Serializable {
    private static final long serialVersionUID = 1195793846780239659L;

    /**
     *酒店ID
     */
    private Long hotelId;

    /**
     * 会议室ID
     */
    private Long meetingId;
}
