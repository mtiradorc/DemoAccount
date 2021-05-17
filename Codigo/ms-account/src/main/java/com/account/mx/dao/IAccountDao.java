package com.account.mx.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.account.mx.dto.AccountDto;
import com.account.mx.dto.TransactionDto;

@Repository
public interface IAccountDao {

	public List<AccountDto> account();
	public String tranfer(Integer tiFromAccount, Integer tiToAccount, Double tdAmount );
	public AccountDto accountByNumber(Integer tiIdAccount);
	public String modifcaAccountAmount(Integer tiIdAccount, Double tdAmount);
	public List<TransactionDto> transacciones();
	public List<TransactionDto> transaccionesByFromAccount(Integer tiAccount);
	public List<TransactionDto> transaccionesByToAccount(Integer tiAccount);
}
