package com.fangcang.hotelinfo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class HotelIdRequestDTO implements Serializable{
	
	
	private static final long serialVersionUID = -6709080327930615312L;
	/**
	 * 酒店id
	 */
	@NotNull(message = "hotelId不能为Null")
	private Long hotelId;
}
