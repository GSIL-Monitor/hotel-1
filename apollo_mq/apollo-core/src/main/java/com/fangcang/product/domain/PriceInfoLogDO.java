package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class PriceInfoLogDO extends BaseDO{
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

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
     * 商家编码
     */
    private String merchantCode;

    /**
     * 价格基准
     */
    private Integer priceStandard;

    /**
     * 调整价格
     */
    private Integer modifyPrice;

    /**
     *调整类型(1、加  , 2、减  ,  3、乘,4、设置为)
     */
    private Integer modifyType;

    /**
     * 调整的数值
     */
    private BigDecimal modifyValue;

    /**
     * 基准价格
     */
    private BigDecimal standardPrice;

}