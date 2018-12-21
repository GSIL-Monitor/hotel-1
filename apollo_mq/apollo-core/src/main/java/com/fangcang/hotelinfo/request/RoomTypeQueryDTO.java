package com.fangcang.hotelinfo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class RoomTypeQueryDTO implements Serializable {
    private static final long serialVersionUID = -841691886179720513L;

    /**
     * 酒店id
     */
    private Long hotelId;

    /**
     * 是否有效
     */
    private Integer isActive;
    
    /**
     * 
     */
    private Long roomTypeId;
}
