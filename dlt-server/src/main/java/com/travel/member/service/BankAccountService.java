package com.travel.member.service;

import java.util.List;

import com.travel.common.dto.member.query.BankAccountQueryDTO;
import com.travel.common.dto.member.response.BankAccountDTO;

/**
 * @Description : 银行账户信息业务操作接口
 * @author : Zhiping Sun
 * @date : 2018年3月9日下午3:20:30
 */
public interface BankAccountService {

	/**
	 * 保存银行账户信息
	 * @param bankAccount
	 */
	public Long saveBankAccount(BankAccountDTO bankAccount);
	
	/**
	 * 修改银行账户信息
	 * @param bankAccount
	 */
	public void updateBankAccount(BankAccountDTO bankAccount);
	
	/**
	 * 删除银行账户信息
	 * @param bankAccountId
	 */
	public void deleteBankAccount(Long bankAccountId);
	
	/**
	 * 查询所有的银行账户信息
	 * @return
	 */
	public List<BankAccountDTO> listBankAccountListByCondition(BankAccountQueryDTO bankAccountQuery);
}