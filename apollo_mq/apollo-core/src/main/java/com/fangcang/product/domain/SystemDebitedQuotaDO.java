package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.util.Date;

@Data
public class SystemDebitedQuotaDO extends BaseDO{

    private Integer id;

    /**
     * 供货单ID
     */
    private Long supplyOrderId;

    /**
     * 供货单Code
     */
    private String supplyOrderCode;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 售卖日期
     */
    private Date saleDate;

    /**
     * 配额数
     */
    private Integer quotaNum;

    /**
     * 配额类型 1 扣配额,2 退配额
     */
    private Integer quotaType;

    /**
     * 配额账号Id
     */
    private Long quotaAccountId;

    /**
     * 订单编码
     */
    private String orderCode;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

}