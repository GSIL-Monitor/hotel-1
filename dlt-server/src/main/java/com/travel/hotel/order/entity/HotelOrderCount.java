package com.travel.hotel.order.entity;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 酒店订单数量
 * @author : Zhiping Sun
 * @date : 2018年4月16日下午11:28:06
 */
@Data
public class HotelOrderCount extends ModifyDTO {
	
	private static final long serialVersionUID = 7695811893259438572L;
	
	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 订单数量
	 */
	private Integer count;

}
