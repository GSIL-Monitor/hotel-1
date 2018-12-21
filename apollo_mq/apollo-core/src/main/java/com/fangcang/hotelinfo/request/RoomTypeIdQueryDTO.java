package com.fangcang.hotelinfo.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RoomTypeIdQueryDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8533482250308764254L;

	/**
	 * 房型ID
	 */
	 @NotNull(message = "roomTypeId不能为Null")
	 private Long roomTypeId;
	
}


