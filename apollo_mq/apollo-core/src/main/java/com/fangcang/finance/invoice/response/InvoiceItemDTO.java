package com.fangcang.finance.invoice.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
public class InvoiceItemDTO implements Serializable{

    private Integer id;

    private Integer invoiceId;

    /**
     * 业务单号
     */
    private String businessCode;

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
     * 订单创建时间
     */
    private String orderCreateDate;

    /**
     * 币种
     */
    private String currency;

    /**
     * 应收
     */
    private BigDecimal receivableAmount;

    /**
     * 开票金额
     */
    private BigDecimal invoiceAmount;

    /**
     * 账单名称
     */
    private String billName;

    /**
     * 开始日期
     */
    private String beginDate;

    /**
     * 结束日期
     */
    private String endDate;

    /**
     * 账单创建时间
     */
    private String billCreateDate;

    private String creator;

    private String createTime;
}
