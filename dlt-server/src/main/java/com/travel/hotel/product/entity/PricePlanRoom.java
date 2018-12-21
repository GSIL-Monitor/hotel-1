package com.travel.hotel.product.entity;

import lombok.Data;

/**
 * @Description : 价格计划房型
 * @author : Zhiping Sun
 * @date : 2018年1月24日上午11:43:51
 */
@Data
public class PricePlanRoom {

	private Long pricePlanId;
	
	private String pricePlanName;
	
	private Long roomTypeId;
	
	private String roomTypeName;
	
	private String bedType;
	
	private String bedTypeText;
	
	private String saleBCurrency;
	
	private String saleCCurrency;
	
	private String baseCurrency;
	
}
