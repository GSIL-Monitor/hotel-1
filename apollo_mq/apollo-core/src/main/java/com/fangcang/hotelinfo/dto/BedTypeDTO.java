package com.fangcang.hotelinfo.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class BedTypeDTO implements Serializable {
	private static final long serialVersionUID = -8503915891069109111L;

	/**
	 * 床型 0.单床 1.大床  2.双床 3.三床  4.四床
	 */
	private Integer bedType;

	/**
	 * 床的长度
	 */
	private String length;
	
	/**
	 *床的宽度
	 */
	private String wide;
	
	/**
	 * 床的数量
	 */
    private Integer num;
}
