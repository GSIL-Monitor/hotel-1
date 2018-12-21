package com.fangcang.b2b.response;

import com.fangcang.b2b.dto.RoomTypeDetailsDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 14:44
 * @Description: 酒店详情ResponseDTO
 */
@Data
public class GetHotelDetailResponseDTO implements Serializable {

    private static final long serialVersionUID = -5778206789851187185L;

    private Long hotelId;

    private List<RoomTypeDetailsDTO> roomTypeDetails;
}
