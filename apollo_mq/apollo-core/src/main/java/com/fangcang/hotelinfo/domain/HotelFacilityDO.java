package com.fangcang.hotelinfo.domain;



import com.fangcang.common.BaseDO;

import lombok.Data;

@Data
public class HotelFacilityDO extends BaseDO{
	
	
	/**
	 * 设施ID
	 * */
	private Long id;
	/**
	 * 酒店Id
	 * */
	private Long hotelId;
	/**
	 * 设施列型  1.宽带网络  2.停车场  3.餐饮设施  4.酒店服务  5.房间设施  6.餐型
	 * */
	private Integer facilityType;
	/**
	 * 设施名称
	 * */
	private String facilityName;
}
