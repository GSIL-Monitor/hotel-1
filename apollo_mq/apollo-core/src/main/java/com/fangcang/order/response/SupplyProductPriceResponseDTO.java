package com.fangcang.order.response;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 供货单产品价格对象
 */
@Data
public class SupplyProductPriceResponseDTO implements Serializable {
    private static final long serialVersionUID = -3690155608491575992L;
    private Integer id;

    /**
     * 供货产品id
     */
    private Integer supplyProductId;

    /**
     * 日期
     */
    private String saleDate;

    /**
     * 售价
     */
    private BigDecimal salePrice;
    /**
     * 底价
     */
    private BigDecimal basePrice;
    /**
     * 实扣配额
     */
    private Integer realQuota;
    /**
     * 剩余配额
     */
    private Integer quotaNum;

    /**
     * 是否可超(1可超  0不可超)
     */
    private Integer overDraft;

    /**
     * 房态(0关房  1开房)
     */
    private Integer quotaState;


}