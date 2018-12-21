package com.travel.member.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.travel.common.dto.member.query.BankAccountQueryDTO;
import com.travel.common.dto.member.response.BankAccountDTO;
import com.travel.member.dao.BankAccountDao;
import com.travel.member.service.BankAccountService;

/**
 * @Description : 银行账户信息业务操作接口实现
 * @author : Zhiping Sun
 * @date : 2018年3月9日下午3:23:16
 */
@Service("bankAccountService")
public class BankAccountServiceImpl implements BankAccountService {
	
	@Autowired
	private BankAccountDao bankAccountDao;

	@Override
	public Long saveBankAccount(BankAccountDTO bankAccount) {
		this.bankAccountDao.saveBankAccount(bankAccount);
		return bankAccount.getBankAccountId();
	}

	@Override
	public void updateBankAccount(BankAccountDTO bankAccount) {
		BankAccountDTO bank = this.bankAccountDao.queryBankAccountById(bankAccount.getBankAccountId());
		BeanUtils.copyProperties(bankAccount, bank);
		this.bankAccountDao.updateBankAccount(bank);
	}

	@Override
	public void deleteBankAccount(Long bankAccountId) {
		this.bankAccountDao.deleteBankAccount(bankAccountId);
	}

	@Override
	public List<BankAccountDTO> listBankAccountListByCondition(BankAccountQueryDTO bankAccountQuery) {
		return this.bankAccountDao.queryBankAccountListByCondition(bankAccountQuery);
	}

}
