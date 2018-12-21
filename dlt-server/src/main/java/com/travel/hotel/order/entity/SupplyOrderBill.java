package com.travel.hotel.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @Description : 供应商订单对账信息
 * @author : Zhiping Sun
 * @date : 2018年1月15日下午5:20:06
 */
@Data
public class SupplyOrderBill {
	
	/**
	 * 订单编码
	 */
	private String orderCode;
	
	/**
	 * 入住人
	 */
	private String guestName;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 入住日期
	 */
	private Date checkInDate;
	
	/**
	 * 离店日期
	 */
	private Date checkOutDate;
	
	/**
	 * 房号
	 */
	private String roomNumber;
	
	/**
	 * 间数
	 */
	private Integer roomCount;
	
	/**
	 * 房费金额
	 */
	private BigDecimal roomPrice;
	
	/**
	 * 应付金额
	 */
	private BigDecimal payableAmount;
	
	/**
	 * 币种
	 */
	private String currency;
	
	/**
	 * 币种描述
	 */
	private String currencyText;
	
}
