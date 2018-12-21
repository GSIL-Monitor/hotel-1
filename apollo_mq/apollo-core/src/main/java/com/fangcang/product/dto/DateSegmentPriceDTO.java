package com.fangcang.product.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/29.
 */
@Data
public class DateSegmentPriceDTO implements Serializable {

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 星期
     */
    private String weeks;

    /**
     * 基准价格
     */
    private BigDecimal standardPrice;

    /**
     * 低价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer basePriceOperateType;

    /**
     * 底价加幅金额
     */
    private BigDecimal basePriceIncreaseAmount;

    /**
     * 团房低价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer groupBaseOperateType;

    /**
     * 团房低价加幅金额
     */
    private BigDecimal groupBaseIncreaseAmount;

    /**
     * B2B渠道调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer b2bOperateType;

    /**
     * B2B加幅金额
     */
    private BigDecimal b2bIncreaseAmount;

    /**
     * 团房售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer groupSaleOperateType;

    /**
     * 团房售价加幅金额
     */
    private BigDecimal groupSaleIncreaseAmount;

    /**
     * 携程售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer ctripOperateType;

    /**
     * 携程售价加幅金额
     */
    private BigDecimal ctripIncreaseAmount;

    /**
     * 去哪儿售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer qunarOperateType;

    /**
     * 去哪儿售价加幅金额
     */
    private BigDecimal qunarIncreaseAmount;

    /**
     * 艺龙售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer elongOperateType;

    /**
     * 艺龙售价加幅金额
     */
    private BigDecimal elongIncreaseAmount;

    /**
     * 同程售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer tongchengOperateType;

    /**
     * 同程售价加幅金额
     */
    private BigDecimal tongchengIncreaseAmount;

    /**
     * 途牛售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer tuniuOperateType;

    /**
     * 途牛售价加幅金额
     */
    private BigDecimal tuniuIncreaseAmount;

    /**
     * 新美大售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer xmdOperateType;

    /**
     * 新美大售价加幅金额
     */
    private BigDecimal xmdIncreaseAmount;

    /**
     * 京东售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer jdOperateType;

    /**
     * 京东售价加幅金额
     */
    private BigDecimal jdIncreaseAmount;

    /**
     * 淘宝售价调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer taobaoOperateType;

    /**
     * 淘宝售价加幅金额
     */
    private BigDecimal taobaoIncreaseAmount;

    /**
     * 去哪儿B2B调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer qunarB2BOperateType;

    /**
     *去哪儿B2B加幅金额
     */
    private BigDecimal qunarB2BIncreaseAmount;

    /**
     * 去哪儿夜宵调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer qunarNgtOperateType;

    /**
     * 去哪儿夜宵加幅金额
     */
    private BigDecimal qunarNgtIncreaseAmount;

    /**
     * 去哪儿美元调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer qunarUsdOperateType;

    /**
     * 去哪儿美元加幅金额
     */
    private BigDecimal qunarUsdIncreaseAmount;

    /**
     * 去哪儿子渠道调整类型(1、加  ,2、减 ,3、乘)
     */
    private Integer qunarSonOperateType;

    /**
     * 去哪儿子渠道加幅金额
     */
    private BigDecimal qunarSonIncreaseAmount;
}
