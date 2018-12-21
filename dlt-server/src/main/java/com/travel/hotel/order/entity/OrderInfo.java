package com.travel.hotel.order.entity;

import com.travel.hotel.order.entity.po.OrderPO;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年1月24日下午9:54:28
 */
@Data
public class OrderInfo extends OrderPO {
	
	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;

}
