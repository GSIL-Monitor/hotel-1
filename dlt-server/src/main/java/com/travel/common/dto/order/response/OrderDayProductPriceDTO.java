package com.travel.common.dto.order.response;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年3月1日下午6:11:43
 */
@Data
public class OrderDayProductPriceDTO implements Serializable {
	
	private static final long serialVersionUID = 1878766861555811842L;
	
	/**
	 * 售卖日期
	 */
	private String saleDate;
	
	/**
	 * 房型名称
	 */
	private String roomTypeName;
	
	/**
	 * 床型名称
	 */
	private String bedTypeName;
	
	/**
	 * 间数
	 */
	private String rooms;
	
	/**
	 * 早餐类型
	 */
	private String breakFastNum;
	
	/**
	 * 售卖价格
	 */
	private String salePrice;
	
	/**
	 * 付款方式
	 */
	private String payMethod;

}
