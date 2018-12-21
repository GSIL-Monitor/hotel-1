package com.fangcang.hotelinfo.response;

import java.io.Serializable;

import lombok.Data;

@Data
public class HotelInfoResponseDTO implements Serializable{
	 
	
	private static final long serialVersionUID = -1115648743487561981L;
	/**
	 * 酒店id
	 */
	private Long hotelId;
}
