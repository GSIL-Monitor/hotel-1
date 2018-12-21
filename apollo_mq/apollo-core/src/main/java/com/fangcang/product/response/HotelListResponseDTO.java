package com.fangcang.product.response;

import com.fangcang.common.BaseDTO;
import com.fangcang.product.dto.RoomTypeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/17.
 */
@Data
public class HotelListResponseDTO extends BaseDTO implements Serializable{
    private static final long serialVersionUID = -3600019488543894565L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 城市名称
     */
    private String cityName;

    /**
     * 是否常用 1 常用 0 不常用
     */
    private Integer isCommonUsed;

    /**
     * 房型集合
     */
    private List<RoomTypeDTO> roomTypeList;
}
