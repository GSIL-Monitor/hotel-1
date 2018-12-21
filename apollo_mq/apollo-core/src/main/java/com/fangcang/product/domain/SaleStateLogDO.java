package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;


@Data
public class SaleStateLogDO extends BaseDO{
    private Integer id;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 售卖状态
     */
    private Integer saleState;
}