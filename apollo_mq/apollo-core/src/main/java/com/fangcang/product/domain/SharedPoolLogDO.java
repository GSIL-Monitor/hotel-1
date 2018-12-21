package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;


@Data
public class SharedPoolLogDO extends BaseDO{
    private Integer id;

    /**
     * 操作类型
     */
    private Integer operateType;

    /**
     * 配额账号ID
     */
    private Long quotaAccountId;

    /**
     * 价格计划ID
     */
    private Long pricePlanId;

    /**
     * 商家编码
     */
    private String merchantCode;

}