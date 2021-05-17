package com.account.mx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.account.mx.dao.IAccountDao;
import com.account.mx.dto.AccountDto;
import com.account.mx.dto.TransactionDto;
import com.account.mx.service.IAccountService;

@Service
public class AccountService implements IAccountService{
	
	@Autowired
	private IAccountDao poAccountDao;

	@Override
	public List<AccountDto> account() {
		return poAccountDao.account();
	}

	@Override
	public String tranfer(Integer tiFromAccount, Integer tiToAccount, Double tdAmount) {
		return poAccountDao.tranfer(tiFromAccount, tiToAccount, tdAmount);
	}

	@Override
	public List<TransactionDto> transacciones() {
		return poAccountDao.transacciones();
	}

	@Override
	public List<TransactionDto> transaccionesByFromAccount(Integer tiAccount) {
		return poAccountDao.transaccionesByFromAccount(tiAccount);
	}

	@Override
	public List<TransactionDto> transaccionesByToAccount(Integer tiAccount) {
		return poAccountDao.transaccionesByToAccount(tiAccount);
	}

}
