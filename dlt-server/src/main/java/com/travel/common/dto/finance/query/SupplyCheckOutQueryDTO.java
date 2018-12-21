package com.travel.common.dto.finance.query;

import lombok.Data;

/**
 * @Description : 供应商出账查询
 * @author : Zhiping Sun
 * @date : 2018年3月15日下午4:24:21
 */
@Data
public class SupplyCheckOutQueryDTO extends CheckOutQueryDTO {
	
	private static final long serialVersionUID = -6254814208506861452L;
	
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	
	/**
	 * 供应商名称
	 */
	private String supplyName;
	
}
