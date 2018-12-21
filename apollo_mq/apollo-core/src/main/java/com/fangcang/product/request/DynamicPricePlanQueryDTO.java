package com.fangcang.product.request;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by ASUS on 2018/5/30.
 */
@Data
public class DynamicPricePlanQueryDTO implements Serializable {

    /**
     * 价格计划ID
     */
    private Long pricePlanId;
    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 房型ID
     */
    private Long roomTypeId;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 是否有效
     */
    private Integer isActive;

    /**
     * 价格计划IDS
     */
    private String pricePlanIds;

    /**
     * 供应商编码
     */
    private String supplyCode;

    /**
     * 供应商名称
     */
    private String supplyName;

    /**
     * 价格计划名称
     */
    private String pricePlanName;

    /**
     * 产品类型(1 散房  2 团房)
     */
    private Integer productType;

    /**
     * 渠道编码
     */
    private String channelCode;

    /**
     * 1 上架 只查上架产品 不传查所有
     */
    private Integer saleState;

    /**
     * 配额类型
     */
    private Integer quotaType;

    /**
     * 房型ID
     */
    private List<Long> roomTypeIds;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;
}
