package com.fangcang.hotelinfo.request;

import java.io.Serializable;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class HotelBaseInfoRequestDTO implements Serializable {
	private static final long serialVersionUID = 2646555245735619073L;

	/**
	 * 酒店ID
	 */
	@NotNull(message = "hotelId不能为Null")
	private  Long  hotelId;

	/**
	 * 商家编码
	 */
	private String merchantCode;

}
