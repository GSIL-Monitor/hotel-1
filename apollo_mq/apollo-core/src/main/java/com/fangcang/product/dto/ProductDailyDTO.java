package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/18.
 */
@Data
public class ProductDailyDTO implements Serializable{
    private static final long serialVersionUID = -4872632681579169996L;

    /**
     * 售卖日期
     */
    private Date saleDate;

    /**
     * 底价
     */
    private BigDecimal basePrice;

    /**
     * 团房低价
     */
    private BigDecimal groupBasePrice;

    /**
     * B2B售价
     */
    private BigDecimal b2bSalePrice;

    /**
     * 团房售价
     */
    private BigDecimal groupSalePrice;

    /**
     * 携程售价
     */
    private BigDecimal ctripSalePrice;

    /**
     * 去哪儿售价
     */
    private BigDecimal qunarSalePrice;

    /**
     * 艺龙售价
     */
    private BigDecimal elongSalePrice;

    /**
     * 同程售价
     */
    private BigDecimal tongchengSalePrice;

    /**
     * 途牛售价
     */
    private BigDecimal tuniuSalePrice;

    /**
     * 新美大售价
     */
    private BigDecimal xmdSalePrice;

    /**
     * 京东售价
     */
    private BigDecimal jdSalePrice;

    /**
     * 淘宝售价
     */
    private BigDecimal taobaoSalePrice;

    /**
     * 去哪儿B2B售价
     */
    private BigDecimal qunarB2BSalePrice;

    /**
     * 去哪儿夜宵售价
     */
    private BigDecimal qunarNgtSalePrice;

    /**
     * 去哪儿美元售价
     */
    private BigDecimal qunarUsdSalePrice;

    /**
     * 去哪儿子渠道售价
     */
    private BigDecimal qunarSonSalePrice;

    /**
     * 配额数
     */
    private Integer quotaNum;

    /**
     * 总配额数
     */
    private Integer allQuotaNum;

    /**
     * 是否可超(1可超  0不可超)
     */
    private Integer overDraft;

    /**
     * 房态(0关房  1开房)
     */
    private Integer quotaState;

    /**
     * 分销商结算币种
     */
    private String showCurrency;

    /**
     * 预定类型 1 预定  2部分可定  3不可订
     */
    private Integer bookType;
}
