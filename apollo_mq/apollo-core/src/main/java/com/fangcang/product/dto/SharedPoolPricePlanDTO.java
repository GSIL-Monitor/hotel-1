package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/5/19.
 */
@Data
public class SharedPoolPricePlanDTO implements Serializable{

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 价格计划名称
     */
    private String pricePlanName;

    /**
     * 是否为当前价格计划(1是 0不是)
     */
    private Integer isCurrentPricePlan;
}
