package com.fangcang.hotelinfo.request;

import java.io.Serializable;
import java.util.List;

import com.fangcang.common.BaseQueryConditionDTO;

import lombok.Data;

@Data
public class HotelListQueryDTO extends BaseQueryConditionDTO implements Serializable{
	 
	private static final long serialVersionUID = 2345856038687767046L;
	
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
     * 商业区编码
     */
    private String businessCode;

    /**
     * 酒店星级
     */
    private List<Integer> hotelStars;

    /**
     * 商家编码
     */
    private String merchantCode;

    /**
     * 是否有效
     */
    private Integer isActive;
 
}
