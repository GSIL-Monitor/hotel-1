package com.travel.common.dto.finance.query;

import java.util.List;

import lombok.Data;

/**
 * @Description : 客户出账查询
 * @author : Zhiping Sun
 * @date : 2018年3月15日下午4:23:16
 */
@Data
public class AgentCheckOutQueryDTO extends CheckOutQueryDTO {
	
	private static final long serialVersionUID = -7749992650089421085L;
	
	/**
	 * 分销商编码
	 */
	private String agentCode;
	
	/**
	 * 分销商名称
	 */
	private String agentName;
	
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	
	/**
	 * 供应商名称
	 */
	private String supplyName;
	
	/**
	 * 订单状态(查询页面属性)
	 */
	private Integer orderState;
	
	/**
	 * 订单状态
	 */
	private List<String> orderStateList;

}
