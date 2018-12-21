package com.travel.member.entity;

import com.travel.common.dto.ModifyDTO;
import lombok.Data;

/**
 * @Description 供应商信息
 * @author Zhiping Sun <szp_9830@163.com>
 * @date 2017年12月31日上午11:38:44
 */
@Data
public class Supply extends ModifyDTO {

	private static final long serialVersionUID = -3211756540622015419L;
	
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
	 * 供应商地址
	 */
	private String address;
	
	/**
	 * 供应商联系电话
	 */
	private String tel;
	
	/**
	 * 供应商传真号码
	 */
	private String fax;
	
	/**
	 * 供应商结算方式
	 */
	private String settlement;
	
	/**
	 * 供应商邮箱
	 */
	private String email;
	
	/**
	 * 供应商联系人
	 */
	private String contacts;
	
	/**
	 * 供应商是否有效
	 */
	private Integer isActive;
	
}
