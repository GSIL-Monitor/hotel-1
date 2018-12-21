package com.travel.common.dto.finance.query;

import java.util.Date;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 申诉查询参数
 * @author : Zhiping Sun
 * @date : 2018年1月17日下午2:24:05
 */
@Data
public class OrderAppealQuery extends GenericQueryDTO {

	private static final long serialVersionUID = -4607938437970139982L;
	
	/**
	 * 订单编号
	 */
	private String orderCode;
	
	/**
	 * 申诉处理人
	 */
	private String handler;
	
	/**
	 * 申诉处理时间
	 */
	private Date handleTime;
	
	/**
	 * 申诉状态
	 */
	private String status;
	
	/**
	 * 申诉状态描述
	 */
	private String statusText;

}
