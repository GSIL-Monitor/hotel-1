package com.travel.common.dto.finance.response;

import com.travel.hotel.order.entity.po.OrderAppealPO;

import lombok.Data;

/**
 * @Description : 订单申诉信息
 * @author : Zhiping Sun
 * @date : 2018年1月17日下午2:14:01
 */
@Data
public class OrderAppealResponseDTO extends OrderAppealPO {
	
	private static final long serialVersionUID = 4505807745136765512L;
	
	/**
	 * 申诉状态描述
	 */
	private String statusText;
	
}
