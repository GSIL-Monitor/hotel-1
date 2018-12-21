package com.travel.common.dto.finance.request;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 反核销参数
 * @author : Zhiping Sun
 * @date : 2018年3月27日下午4:41:39
 */
@Data
public class ReverseRequestDTO extends ModifyDTO {

	private static final long serialVersionUID = 557976879615974121L;
	
	/**
	 * 销账id
	 */
	private Long billOffId;
	
	/**
	 * 销账机构类型(F:分销商;S:供应商)
	 * 不需要页面传递参数。
	 */
	private String orgType;

}
