package com.fangcang.hotelinfo.request;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ASUS on 2018/6/25.
 */
@Data
public class HotelQueryRequestDTO implements Serializable {
    private static final long serialVersionUID = -8283664508854973990L;

    /**
     * 国家编码
     */
    private String countryCode;

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
     * 当前页数
     */
    private Integer pageNo = 1;

    /**
     * 单页记录数 默认值为15
     */
    private Integer pageSize = 15;

    /**
     * 酒店列表
     */
    private List<Long> hotelList;
}
