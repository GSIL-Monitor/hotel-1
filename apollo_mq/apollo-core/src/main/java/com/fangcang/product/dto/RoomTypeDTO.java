package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class RoomTypeDTO implements Serializable{

    private static final long serialVersionUID = -7317185998178038948L;
    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 宽带  0无，1免费，2收费
     */
    private Integer broadBand;

    /**
     * 价格计划列表
     */
    private List<PricePlanInfoDTO> pricePlanList;

    /**
     * 床型集合
     */
    private List<BedTypeDTO> bedTypeList;

    /**
     * 是否有价格：0无，1有
     */
    private Integer hasPrice=0;
}
