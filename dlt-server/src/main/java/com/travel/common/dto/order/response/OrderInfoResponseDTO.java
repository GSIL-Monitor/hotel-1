package com.travel.common.dto.order.response;

import java.io.Serializable;

import com.travel.hotel.order.entity.po.OrderPO;

import lombok.Data;

/**
 * @Description : 订单信息
 * @author : Zhiping Sun
 * @date : 2018年1月22日上午9:21:26
 */
@Data
public class OrderInfoResponseDTO extends OrderPO implements Serializable {

	private static final long serialVersionUID = -6513572199965692477L;
	
	/**
	 * 酒店名称
	 */
	private String hotelName;
	
	/**
	 * 订单结算方式描述
	 */
	private String settlementText;
	
	/**
	 * 订单状态描述
	 */
	private String orderStateText;
	
	/**
	 * 订单支付方式描述
	 */
	private String payMethodText;
	
	/**
	 * 订单支付状态描述
	 */
	private String payStateText;
	
	/**
	 * 订单售卖币种描述
	 */
	private String saleCurrencyText;
	
	/**
	 * 订单低价币种描述
	 */
	private String baseCurrencyText;
	
	/**
	 * 渠道编码描述
	 */
	private String channelCodeText;
	
	/**
	 * 渠道状态描述
	 */
	private String channelStateText;
	
	/**
	 * 订单锁单状态描述
	 */
	private String financeLockStateText;
	
}
