package com.fangcang.order.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
public class OrderCheckDetailDTO implements Serializable {
    private static final long serialVersionUID = -7928509962843278399L;

    private Integer id;

    /**
     * 订单id
     */
    private Integer oderId;

    /**
     * 供货单id
     */
    private Integer supplyOrderId;

    /**
     * 对象类型为1， 此为供货产品id；对象类型为2，此为供货附加项id
     */
    private Integer supplyProductId;

    /**
     * 日期段，开始时间
     */
    private String checkinDate;

    /**
     * 日期段，结束时间
     */
    private String checkoutDate;

    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 天数
     */
    private Integer days;

    /**
     * 底价
     */
    private BigDecimal basePrice;

    private String creator;

    private Date createTime;

    private String modifier;

    private Date modifyTime;

}