package com.travel.common.dto.finance.response;

import com.travel.finance.entity.OrgCheckOutDetail;

import lombok.Data;

/**
 * @Description : 机构出账明细信息
 * @author : Zhiping Sun
 * @date : 2018年3月14日下午4:17:55
 */
@Data
public class OrgCheckOutDetailResponseDTO extends OrgCheckOutDetail {
	
	private static final long serialVersionUID = -5123917570560336033L;
	
	/**
	 * 订单状态中文描述
	 */
	private String orderStateText;
	
	/**
	 * 支付方式中文描述
	 */
	private String payMethodText;
	
	/**
	 * 晚数
	 */
	private Integer nights;
	
}
