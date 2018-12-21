package com.fangcang.product.response;

import com.fangcang.product.dto.RoomTypeDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class ToAddPricePlanResponseDTO implements Serializable{

    private static final long serialVersionUID = 452021121210560283L;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     *酒店名称
     */
    private String hotelName;

    /**
     * 房型列表
     */
    private List<RoomTypeDTO> roomTypeList;

    /**
     * 附加项说明
     */
    private String additionalStr;

    /**
     * 取消政策
     */
    private String cancelPolicy;

    /**
     * 面房政策(1 全陪免半 2 8免半16兔1)
     */
    private String [] freeRoomPolicy;

}
