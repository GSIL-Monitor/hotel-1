package com.travel.common.dto.member.query;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description 分销商查询类
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午11:47:38
 */
@Data
public class AgentQueryDTO extends GenericQueryDTO {

	private static final long serialVersionUID = 7685357796717624369L;
	
	/**
	 * 分销商id
	 */
	private Long agentId;
	
	/**
	 * 分销商编码
	 */
	private String agentCode;
	
	/**
	 * 分销商名称
	 */
	private String agentName;
	
	/**
	 * 分销商是否有效
	 */
	private Integer isActive;

}
