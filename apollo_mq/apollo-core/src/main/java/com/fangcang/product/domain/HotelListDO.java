package com.fangcang.product.domain;

import com.fangcang.common.BaseDO;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by ASUS on 2018/6/2.
 */
@Data
public class HotelListDO extends BaseDO implements Serializable {

    /**
     * 酒店ID
     */
    private Long hotelId;
    /**
     * 酒店名称
     */
    private String hotelName;
    /**
     * 酒店英文名称
     */
    private String engHotelName;
    /**
     * 国家编码
     */
    private String country;

    /**
     *国家名称
     */
    private String countryName;
    /**
     * 城市编码
     */
    private String cityCode;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 商业区编码
     */
    private String businessCode;

    /**
     * 是否常用 1 常用 0 不常用
     */
    private Integer isCommonUsed;
}
