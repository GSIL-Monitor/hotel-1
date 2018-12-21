package com.fangcang.hotelinfo.request;

import java.io.Serializable;

import com.fangcang.common.BaseQueryConditionDTO;

import lombok.Data;

@Data
public class CommonUsedHotelRequestDTO extends BaseQueryConditionDTO implements Serializable{
	 
	private static final long serialVersionUID = -5532374694995270217L;
	/**
	 * 酒店名称
	 * */
	private String hotelName;
	/**
	 * 城市编码
	 */
	private String cityCode;
	/**
	 * 商家编码
	 */
	private String merchantCode;

	/**
	 * 是否有效
	 */
	private Integer isActive;
}
