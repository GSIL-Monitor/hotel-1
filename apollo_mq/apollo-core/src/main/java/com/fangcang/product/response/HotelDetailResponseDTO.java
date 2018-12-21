package com.fangcang.product.response;

import com.fangcang.product.dto.RoomTypeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class HotelDetailResponseDTO implements Serializable{

    private static final long serialVersionUID = -8042778525282532931L;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 房型集合
     */
    private List<RoomTypeDTO> roomTypeList;
}
