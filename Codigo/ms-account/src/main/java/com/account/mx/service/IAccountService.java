package com.account.mx.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.account.mx.dto.AccountDto;
import com.account.mx.dto.TransactionDto;

@Service
public interface IAccountService {

	public List<AccountDto> account();
	public String tranfer(Integer tiFromAccount, Integer tiToAccount, Double tdAmount );
	public List<TransactionDto> transacciones();
	public List<TransactionDto> transaccionesByFromAccount(Integer tiAccount);
	public List<TransactionDto> transaccionesByToAccount(Integer tiAccount);
}
