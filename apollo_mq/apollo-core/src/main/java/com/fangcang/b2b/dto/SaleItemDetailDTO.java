package com.fangcang.b2b.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther: yanming.li@fangcang.com
 * @Date: 2018/6/30 14:50
 * @Description: 产品每日详情
 */
@Data
public class SaleItemDetailDTO implements Serializable {

    private static final long serialVersionUID = -7699067401139928731L;

    /**
     * 日期
     */
    private Date saleDate;

    /**
     * 底价
     */
    private BigDecimal basePrice;

    /**
     * B2B售价
     */
    private BigDecimal b2bSalePrice;
    /**
     * 团房低价
     */
    private BigDecimal groupBasePrice;
    /**
     * 团房售价
     */
    private BigDecimal groupSalePrice;
    /**
     * 配额数量
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
    /**
     * 展示币种
     */
    private String showCurrency;

}
