package com.travel.hotel.order.entity;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

/**
 * @Description : 客户订单对账信息
 * @author : Zhiping Sun
 * @date : 2018年1月15日下午5:20:06
 */
@Data
public class OrderBill {
	
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
	 * 支付方式
	 */
	private String payMethod;
	
	/**
	 * 价格计划id
	 */
	private Long pricePlanId;
	
	/**
	 * 价格计划名称
	 */
	private String pricePlanName;
	
	/**
	 * 房型id
	 */
	private Long roomTypeId;
	
	/**
	 * 房型名称
	 */
	private String roomTypeName;
	
	/**
	 * 房号
	 */
	private String roomNumber;
	
	/**
	 * 晚数
	 */
	private Integer nightCount;
	
	/**
	 * 间数
	 */
	private Integer roomCount;
	
	/**
	 * 房费金额
	 */
	private BigDecimal roomPrice;
	
	/**
	 * 应收金额
	 */
	private BigDecimal receiveAmount;
	
	/**
	 * 应付佣金
	 */
	private BigDecimal commission;
	
	/**
	 * 币种
	 */
	private String currency;
	
	/**
	 * 币种描述
	 */
	private String currencyText;

}
