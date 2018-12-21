package com.fangcang.hotelinfo.request;

import java.io.Serializable;

import com.fangcang.common.BaseDTO;

import lombok.Data;

@Data
public class RoomTypeSortDTO extends BaseDTO implements Serializable{

	private static final long serialVersionUID = -4007963807983536711L;
	/**
	 * 房型id
	 */
	private Long roomTypeId;

	/**
	 * 排序字段
	 */
	private Integer sort;
}
