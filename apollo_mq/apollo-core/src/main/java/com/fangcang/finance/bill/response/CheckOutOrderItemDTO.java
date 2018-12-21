package com.fangcang.finance.bill.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutOrderItemDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 明细类型
     */
    private Integer itemType;

    /**
     * 明细名称
     */
    private String itemName;

    /**
     * 房型名称
     */
    private String roomTypeName;

    /**
     * 价格计划名称
     */
    private String ratePlanName;

    /**
     * 入住人
     */
    private String guest;

    /**
     * 入住日期
     */
    private String checkInDate;

    /**
     * 离店日期
     */
    private String checkOutDate;

    /**
     * 晚数
     */
    private Integer nightNum;

    /**
     * 间数
     */
    private Integer roomNum;

    /**
     * 币种
     */
    private String currency;

    /**
     * 单价
     */
    private BigDecimal price;

    /**
     * 小计
     */
    private BigDecimal sum;
}
