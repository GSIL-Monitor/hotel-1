package com.fangcang.product.request;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/21.
 */
@Data
public class ProductDailyInfoQueryDTO implements Serializable {
    private static final long serialVersionUID = -6180771363970763822L;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

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
     * 价格计划ID
     */
    private String pricePlanIds;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 是否有效
     */
    private Integer isActive;

    /**
     * 分销商编码  如果传了则指定的渠道售价转换为分销商的结算币种
     */
    private String agentCode;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 最小价格
     */
    private BigDecimal minPrice;

    /**
     * 最大价格
     */
    private BigDecimal maxPrice;

    /**
     * 产品类型(1 散房  2 团房)
     */
    private Integer productType;

    /**
     * 1 上架 只查上架产品 不传查所有
     */
    private Integer saleState;

    /**
     * 下单类型(1 散房  2 团房)
     */
    private Integer orderType;
}
