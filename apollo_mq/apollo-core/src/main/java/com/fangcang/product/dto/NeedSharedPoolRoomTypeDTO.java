package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class NeedSharedPoolRoomTypeDTO implements Serializable{
    private static final long serialVersionUID = 3555629539475068385L;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    private List<PricePlanDTO> pricePlanList;
}
