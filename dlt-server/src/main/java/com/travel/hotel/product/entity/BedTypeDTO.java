package com.travel.hotel.product.entity;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年1月27日下午2:54:43
 */
@Data
public class BedTypeDTO extends ModifyDTO {
	
	private static final long serialVersionUID = 2231054434058507634L;
	
	private String bedType;
	
	private String bedTypeText;

}
