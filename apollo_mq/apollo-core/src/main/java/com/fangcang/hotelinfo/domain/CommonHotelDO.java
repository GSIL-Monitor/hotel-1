package com.fangcang.hotelinfo.domain;

import com.fangcang.common.BaseDO;

import lombok.Data;

@Data
public class CommonHotelDO extends BaseDO{
	/**
	 * 是否常用ID
	 */
	private Long id;
	
	/**
	 * 酒店ID
	 */
	private Long hotelId;
	
	/**
	 * 商家编码
	 */
	private String merchantCode;
	
	/**
	 * 推荐等级
	 */
    private  Integer recommendedLevel;
}
