package com.fangcang.hotelinfo.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommonHotelResponseDTO implements Serializable{
	
	private static final long serialVersionUID = 7497391449189432426L;
	/**
	 * 酒店Id
	 * */
	private Long hotelId;
	/**
	 * 酒店Id
	 * */
	private Integer isCommonUsed;
}
