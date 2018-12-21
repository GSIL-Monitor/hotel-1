package com.travel.member.dao;

import java.util.List;

import com.travel.common.dto.member.query.BankAccountQueryDTO;
import com.travel.common.dto.member.response.BankAccountDTO;

/**
 * @Description : 银行账号操作数据接口
 * @author : Zhiping Sun
 * @date : 2018年3月9日下午3:10:02
 */
public interface BankAccountDao {

	/**
	 * 保存银行账户信息
	 * @param bankAccount
	 */
	public void saveBankAccount(BankAccountDTO bankAccount);
	
	/**
	 * 修改银行账户信息
	 * @param bankAccount
	 */
	public void updateBankAccount(BankAccountDTO bankAccount);
	
	/**
	 * 查询银行账户信息
	 * @param bankAccountId
	 */
	public BankAccountDTO queryBankAccountById(Long bankAccountId);
	
	/**
	 * 删除银行账户信息
	 * @param bankAccountId
	 */
	public void deleteBankAccount(Long bankAccountId);
	
	/**
	 * 查询银行账户信息集合
	 * @param bankAccountQuery
	 * @return
	 */
	public List<BankAccountDTO> queryBankAccountListByCondition(BankAccountQueryDTO bankAccountQuery);
	
}
