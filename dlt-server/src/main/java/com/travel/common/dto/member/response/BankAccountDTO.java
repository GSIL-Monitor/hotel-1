package com.travel.common.dto.member.response;

import com.travel.common.dto.ModifyDTO;

import lombok.Data;

/**
 * @Description : 
 * @author : Zhiping Sun
 * @date : 2018年3月9日下午3:11:26
 */
@Data
public class BankAccountDTO extends ModifyDTO {

	private static final long serialVersionUID = 8928398875549385476L;

	/**
	 * 银行账号id
	 */
	private Long bankAccountId;
	
	/**
	 * 银行账户
	 */
	private String bankNo;
	
	/**
	 * 银行账户名称
	 */
	private String bankName;
	
	/**
	 * 户主姓名
	 */
	private String bankUserName;
	
	/**
	 * 账户币种
	 */
	private String currency;
	
	/**
	 * 是否有效(1:有效;0:无效)
	 */
	private Integer isActive;
	
	/**
	 * 所属部门
	 */
	private String dept;
}
