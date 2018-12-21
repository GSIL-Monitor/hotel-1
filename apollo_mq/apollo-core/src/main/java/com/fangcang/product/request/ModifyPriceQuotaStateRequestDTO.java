package com.fangcang.product.request;

import com.fangcang.common.BaseDTO;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class ModifyPriceQuotaStateRequestDTO extends BaseDTO implements Serializable {
    private static final long serialVersionUID = 530041791199709100L;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 调价基准
     */
    @NotNull(message = "priceStandard不能为Null")
    private Integer priceStandard;

    /**
     * 调整价格
     */
    @NotNull(message = "modifyPriceStr不能为Null")
    private Integer []  modifyPriceStr;

    @NotNull(message = "pricePlanId不能为Null")
    private Long pricePlanId;

    /**
     * 配额账户ID
     */
    @NotNull(message = "quotaAccountId不能为Null")
    private Long quotaAccountId;

    /**
     * 配额账号名称
     */
    private String quotaAccountName;

    /**
     * 是否共享(1 共享  ,0 不共享)
     */
    private Integer isShare;

    /**
     * 售卖日期
     */
    private Date saleDate;

    /**
     * 基准价格
     */
    private BigDecimal standardPrice;

    /**
     * 底价coax类型(1、加  ,2、减 ,3、乘)
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

    /**
     * 房态设置(-1不变  0关房  1开房)
     */
    private Integer quotaState;

    /**
     * 配额的操作类型(1 增加 , 2减少, 3设置为)
     */
    private Integer operateType;

    /**
     * 配额数量
     */
    private Integer quotaNum;

    /**
     * 是否可超(1可超  0不可超  -1 不变)
     */
    private Integer overDraft;


    private Date startDate;

    private Date endDate;

    /**
     * 日历界面选中的日期
     */
    private List<Date> saleDateList;
}
