package com.fangcang.hotelinfo.response;

import com.fangcang.hotelinfo.dto.ImageTypeDTO;
import com.fangcang.hotelinfo.dto.MeetingRoomImageDTO;
import com.fangcang.hotelinfo.dto.RoomTypeImageDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 15:12
 * @Description:
 */
@Data
public class HotelImageListResponseDTO implements Serializable {


    private static final long serialVersionUID = -2286474848535775568L;

    /**
     * 图片类型列表
     */
    private List<ImageTypeDTO> imageTypeList;

    /**
     * 房型图片列表
     */
    private List<RoomTypeImageDTO> roomTypeImageList;
    
    /**
     * 会议室图片列表
     */
    private List<MeetingRoomImageDTO> meetingRoomImageList;
}
