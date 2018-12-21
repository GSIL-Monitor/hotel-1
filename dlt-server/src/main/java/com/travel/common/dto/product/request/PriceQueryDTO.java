package com.travel.common.dto.product.request;

import java.util.Date;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年1月29日上午11:42:39
 */
@Data
public class PriceQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = -7846691058090231295L;
	
	/**
	 * 价格计划id
	 */
	private Long pricePlanId;
	
	/**
	 * 入住日期
	 */
	private Date saleDate;
	
}
