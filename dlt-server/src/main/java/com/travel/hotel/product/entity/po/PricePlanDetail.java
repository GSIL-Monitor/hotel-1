package com.travel.hotel.product.entity.po;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PricePlanDetail {

    private Date saleDate;

    private String baseCurrency;

    private Integer isActive;

    private Integer payMethod;

    private Long pricePlanId;

    private String pricePlanName;

    private String saleCCurrency;

    private String supplyCode;

    private String quotaState;

    private Integer quotaNum;

    private Integer overdraft;

    private BigDecimal basePrice;

    /**
     * 渠道币种（B2B,OTA渠道都是这个币种）
     */
    private String saleChannelCurrency;

    /**
     * 携程售价
     */
    private BigDecimal ctripPrice;

    /**
     * 早餐
     */
    private String breakfastNum;

    private Integer bookDays;

    private Integer bookTime;

    private Integer occupancyType;

    private Integer occupancyDays;

    private Integer cancelType = 1;

    private Integer cancelDays;

    private String cancelTime;

    private String cancelRemark;

    private Integer bookRooms;

}