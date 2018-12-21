package com.fangcang.hotelinfo.saas.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class RoomTypeBedTypeRelaDTO implements Serializable {
	private static final long serialVersionUID = -6983900729157733548L;
	/**房型ID*/
	private Long roomTypeId ;
	/**床型CODE*/
	private String bedTypecd ;
	/**尺寸长*宽，如200CM*180CM	VARCHAR2(20)*/
	private String bedSize ;
	/**床数,默认1*/
	private Integer bedNum ;
	
}
