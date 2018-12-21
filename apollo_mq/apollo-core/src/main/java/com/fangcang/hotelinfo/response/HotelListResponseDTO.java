package com.fangcang.hotelinfo.response;

import java.io.Serializable;

import com.fangcang.common.BaseDTO;

import lombok.Data;

@Data
public class HotelListResponseDTO  implements Serializable{
	
	private static final long serialVersionUID = -7923281500959455257L;

	/**
     * 酒店ID
     */
    private Long hotelId;

    /**
     * 酒店名称
     */
    private String hotelName;

    /**
     * 城市名称
     */
    private String cityName;
    
    /**
     * 城市编码
     */
    private String cityCode;

    /**
     * 国家编码
     */
    private String countryCode;

    /**
     * 国家名称
     */
    private String countryName;

    /**
     * 是否常用 1 常用 0 不常用
     */
    private Integer isCommonUsed;
}
