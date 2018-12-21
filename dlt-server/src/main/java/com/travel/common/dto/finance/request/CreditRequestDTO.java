package com.travel.common.dto.finance.request;

import java.math.BigDecimal;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 客户挂账参数
 * @author : Zhiping Sun
 * @date : 2018年1月17日上午9:42:02
 */
@Data
public class CreditRequestDTO extends ModifyDTO {
	
	private static final long serialVersionUID = -3531141240777962379L;
	
	/**
	 * 客户编码
	 */
	private String agentCode;
	
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
