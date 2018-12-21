package com.travel.hotel.product.entity;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年1月27日下午2:41:11
 */
@Data
public class BreakfastDTO extends ModifyDTO {

	private static final long serialVersionUID = 3807760466100511176L;
	
	private String breakfastNum;
	
	private String breakfastNumText;
}
