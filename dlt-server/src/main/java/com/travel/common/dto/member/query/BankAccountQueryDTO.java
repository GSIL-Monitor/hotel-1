package com.travel.common.dto.member.query;

import com.travel.common.dto.GenericQueryDTO;

import lombok.Data;

/**
 * @Description : 银行账户查询类
 * @author : Zhiping Sun
 * @date : 2018年3月9日下午4:23:41
 */
@Data
public class BankAccountQueryDTO extends GenericQueryDTO {
	
	private static final long serialVersionUID = 2831579182121922732L;
	
	/**
	 * 银行账户
	 */
	private String bankNo;
	
	/**
	 * 银行账户名称
	 */
	private String bankName;
	
	/**
	 * 银行账户是否有效
	 */
	private Integer isActive;

}
