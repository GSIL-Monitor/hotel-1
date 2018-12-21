package com.travel.common.dto.product.request;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年2月8日下午2:08:57
 */
@Data
public class PricePlanQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = 171497708996737983L;

	/**
	 * 酒店id
	 */
	private Long hotelId;
	
	/**
	 * 供应商编码
	 */
	private String supplyCode;
}
