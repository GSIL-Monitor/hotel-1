package com.travel.finance.entity;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 挂账记录
 * @author : Zhiping Sun
 * @date : 2018年1月17日上午10:14:25
 */
@Data
public class CreditItem extends ModifyDTO {
	
	private static final long serialVersionUID = 7205922271494219727L;
	
	/**
	 * 主键id
	 */
	private Long itemId;
	
	/**
	 * 订单id
	 */
	private Long orderId;
	
	/**
	 * 挂账类型(1:挂账, 2:取消)
	 */
	private Integer creditType;
	
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	/**
	 * 订单结算方式
	 */
	private String checkOutType;
	
	/**
	 * 客户挂账金额
	 */
	private BigDecimal orderCreditAmount;
	
	/**
	 * 供应商挂账金额
	 */
	private BigDecimal supplyCreditAmount;

}
