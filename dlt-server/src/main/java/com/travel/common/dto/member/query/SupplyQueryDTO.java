package com.travel.common.dto.member.query;

import com.travel.common.dto.GenericQueryDTO;
import lombok.Data;

/**
 * @Description 供应商查询类
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午11:47:38
 */
@Data
public class SupplyQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = 7685357796717624369L;
	
	/**
	 * 供应商id
	 */
	private Long supplyId;
	
	/**
	 * 供应商编码
	 */
	private String supplyCode;
	
	/**
	 * 供应商名称
	 */
	private String supplyName;
	
	/**
	 * 供应商是否有效
	 */
	private Integer isActive;

}
