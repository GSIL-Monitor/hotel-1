package com.fangcang.finance.bill.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckOutOrderDTO implements Serializable {
    private static final long serialVersionUID = -6147279044482949578L;

    /**
     * 财务状态
     */
    private Integer accountStatus;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 订单号
     */
    private String orderCode;

    /**
     * 酒店名称
     */
    private String hotelName;

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
     * 间夜
     */
    private Integer roomNight;

    /**
     * 币种
     */
    private String currency;

    /**
     * 客户单号
     */
    private String customerOrderCode;

    /**
     * 应收
     */
    private BigDecimal receivableAmount;

    /**
     * 实收
     */
    private BigDecimal paidinAmount;

    /**
     * 本次要收
     */
    private BigDecimal currPaidinAmount;

    /**
     * 订单创建时间
     */
    private String orderCreateDate;

    /**
     * 结算方式：1月结 2半月结 3周结 4单结 5日结
     */
    private Integer balanceMethod;

    /**
     * 订单明细数量
     */
    private Integer orderItemCount;
}
