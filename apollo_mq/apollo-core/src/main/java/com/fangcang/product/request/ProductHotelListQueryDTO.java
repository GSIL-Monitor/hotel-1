package com.fangcang.product.request;

import com.fangcang.common.BaseQueryConditionDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by ASUS on 2018/5/17.
 */
@Data
public class ProductHotelListQueryDTO extends BaseQueryConditionDTO implements Serializable{
    private static final long serialVersionUID = -5026553331604985791L;

    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 产品经理ID
     */
    private Long productManagerId;

    /**
     * 产品经理名称
     */
    private String productManagerName;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 商家ID
     */
    private Long merchantId;

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 是否查询产品信息
     */
    private Boolean hasQueryProduct;
}
