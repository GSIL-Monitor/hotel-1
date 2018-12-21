package com.fangcang.hotelinfo.response;

import com.fangcang.common.BaseDTO;
import com.fangcang.hotelinfo.dto.MeetingRoomDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/7.
 */
@Data
public class MeetingListResponseDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = -1721813081089898657L;

    /**
     * 会议室列表
     */
    private List<MeetingRoomDTO> meetingList;
}
