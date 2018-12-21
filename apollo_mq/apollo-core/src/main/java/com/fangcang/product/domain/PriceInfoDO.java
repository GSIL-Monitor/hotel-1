package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceInfoDO extends BaseDO{
    /**
     * 主键ID
     */
    private Integer priceInfoId;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 售卖日期
     */
    private Date saleDate;

    /**
     * 底价操作类型(当无基准价的时候，取值为 ：加、减、乘),其他为设置为
     */
    private Integer basePriceOperateType;

    /**
     * B2B散房低价
     */
    private BigDecimal basePrice;

    /**
     * 团房底价操作类型
     */
    private Integer groupBaseOperateType;

    /**
     * 团房底价
     */
    private BigDecimal groupBasePrice;

    /**
     * B2B渠道调整类型
     */
    private Integer b2bOperateType;

    /**
     * B2B散房售价
     */
    private BigDecimal b2bSalePrice;

    /**
     * 团房售价调整类型
     */
    private Integer groupSaleOperateType;

    /**
     * B2B团房售价
     */
    private BigDecimal groupSalePrice;

    /**
     * 携程售价调整类型
     */
    private Integer ctripOperateType;

    /**
     * 携程售价
     */
    private BigDecimal ctripSalePrice;

    /**
     * 去哪儿售价调整类型
     */
    private Integer qunarOperateType;

    /**
     * 去哪儿售价
     */
    private BigDecimal qunarSalePrice;

    /**
     * 艺龙售价调整类型
     */
    private Integer elongOperateType;

    /**
     * 艺龙售价
     */
    private BigDecimal elongSalePrice;

    /**
     * 同程售价调整类型
     */
    private Integer tongchengOperateType;

    /**
     * 同程售价
     */
    private BigDecimal tongchengSalePrice;

    /**
     * 途牛售价调整类型
     */
    private Integer tuniuOperateType;

    /**
     * 途牛售价
     */
    private BigDecimal tuniuSalePrice;

    /**
     * 新美大调整类型
     */
    private Integer xmdOperateType;

    /**
     * 新美大售价
     */
    private BigDecimal xmdSalePrice;

    /**
     * 京东售价调整类型
     */
    private Integer jdOperateType;

    /**
     * 京东售价
     */
    private BigDecimal jdSalePrice;

    /**
     * 淘宝售价调整类型
     */
    private Integer taobaoOperateType;

    /**
     * 淘宝售价
     */
    private BigDecimal taobaoSalePrice;

    /**
     * 去哪儿B2B售价调整类型
     */
    private Integer qunarB2BOperateType;

    /**
     * 去哪儿B2B售价
     */
    private BigDecimal qunarB2bSalePrice;

    /**
     * 去哪儿夜宵调整类型
     */
    private Integer qunarNgtOperateType;

    /**
     * 去哪儿夜宵售价
     */
    private BigDecimal qunarNgtSalePrice;

    /**
     * 去哪儿美元调整类型
     */
    private Integer qunarUsdOperateType;

    /**
     * 去哪儿美元售价
     */
    private BigDecimal qunarUsdSalePrice;

    /**
     * 去哪儿子渠道调整类型
     */
    private Integer qunarSonOperateType;

    /**
     * 去哪儿子渠道售价
     */
    private BigDecimal qunarSonSalePrice;

}