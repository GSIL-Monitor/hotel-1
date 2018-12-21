package com.fangcang.hotelinfo.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: Lyming
 * @Date: 2018/5/21 15:09
 * @Description:
 */
@Data
public class RoomTypeImageDTO implements Serializable {

    private static final long serialVersionUID = -6370293534124573363L;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 图片列表
     */
    private List<ImageInfoDTO> imageList;

}
