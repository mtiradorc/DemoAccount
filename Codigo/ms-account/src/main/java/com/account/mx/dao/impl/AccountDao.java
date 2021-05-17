package com.account.mx.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.account.mx.dao.IAccountDao;
import com.account.mx.dto.AccountDto;
import com.account.mx.dto.OwnerDto;
import com.account.mx.dto.TransactionDto;

@Repository
public class AccountDao implements IAccountDao {

	@Autowired
	private EntityManager entityManager;

	@Transactional
	@Modifying
	@Override
	public List<AccountDto> account() {
		List<AccountDto> laAccountDtos = new ArrayList<AccountDto>();
		AccountDto loAccountDto = null;
		OwnerDto loOwnerDto = null;
		Query query = entityManager.createNativeQuery(
				"select a.idAccount, a.account, a.balance, o.idOwner, o.owner, a.createdAt, a.amount from account a inner join owner o on o.idOwner = a.idOwner");
		List<Object[]> list = query.getResultList();
		if (list != null) {
			for (Object[] laAccount : list) {
				loAccountDto = new AccountDto();
				loOwnerDto = new OwnerDto();
				loAccountDto.setIdAccount(laAccount[0] != null ? Integer.parseInt(laAccount[0].toString()) : null);
				loAccountDto.setAccount(laAccount[1] != null ? Integer.parseInt(laAccount[1].toString()) : null);
				loAccountDto.setBalance(laAccount[2] != null ? Double.parseDouble(laAccount[2].toString()) : null);
				loOwnerDto.setIdOwner(laAccount[3] != null ? Integer.parseInt(laAccount[3].toString()) : null);
				loOwnerDto.setOwner(laAccount[4] != null ? Long.parseLong(laAccount[4].toString()) : null);
				loAccountDto.setCreatedAt(laAccount[5] != null ? laAccount[5].toString() : null);
				loAccountDto.setAmount(laAccount[6] != null ? Double.parseDouble(laAccount[6].toString()) : null);
				loAccountDto.setOwner(loOwnerDto);
				laAccountDtos.add(loAccountDto);
			}
		}
		return laAccountDtos;
	}

	@Transactional
	@Modifying
	@Override
	public String tranfer(Integer tiFromAccount, Integer tiToAccount, Double tdAmount) {
		System.out.println("AccountDao -> tranfer()");
		String tsResult = "Exito";
		AccountDto loFromAccountDto = null;
		AccountDto loToAccountDto = null;
		loFromAccountDto = accountByNumber(tiFromAccount);
		loToAccountDto = accountByNumber(tiToAccount);

		if (loFromAccountDto.getAmount() > 0) {
			if (loFromAccountDto.getAmount() >= tdAmount) {
				modifcaAccountAmount(loFromAccountDto.getIdAccount(), loFromAccountDto.getAmount() - tdAmount);
				modifcaAccountAmount(loToAccountDto.getIdAccount(), loToAccountDto.getAmount() + tdAmount);
				Query query = entityManager.createNativeQuery(
						"INSERT INTO TRANSACTION (idFromAccount, idAccount, amount, sentAt) VALUES (?, ?, ?, ?)")
				.setParameter(1, loFromAccountDto.getIdAccount())
				.setParameter(2, loToAccountDto.getIdAccount())
				.setParameter(3, tdAmount)
				.setParameter(4, new Date());
				query.executeUpdate();
				
			} else {
				tsResult = "El usuario no tiene suficientes fondos";
			}
		} else {
			tsResult = "El usuario no tiene suficientes fondos";
		}

		return tsResult;
	}

	@Transactional
	@Modifying
	@Override
	public AccountDto accountByNumber(Integer tiIdAccount) {
		AccountDto loAccountDto = null;
		OwnerDto loOwnerDto = null;
		Query query = entityManager.createNativeQuery(
				"select a.idAccount, a.account, a.balance, o.idOwner, o.owner, a.createdAt, a.amount from account a inner join owner o on o.idOwner = a.idOwner where a.account = :account");
		query.setParameter("account", tiIdAccount);
		System.out.println(query.toString());
		List<Object[]> list = query.getResultList();
		if (list != null) {
			for (Object[] laAccount : list) {
				loAccountDto = new AccountDto();
				loOwnerDto = new OwnerDto();
				loAccountDto.setIdAccount(laAccount[0] != null ? Integer.parseInt(laAccount[0].toString()) : null);
				loAccountDto.setAccount(laAccount[1] != null ? Integer.parseInt(laAccount[1].toString()) : null);
				loAccountDto.setBalance(laAccount[2] != null ? Double.parseDouble(laAccount[2].toString()) : null);
				loOwnerDto.setIdOwner(laAccount[3] != null ? Integer.parseInt(laAccount[3].toString()) : null);
				loOwnerDto.setOwner(laAccount[4] != null ? Long.parseLong(laAccount[4].toString()) : null);
				loAccountDto.setCreatedAt(laAccount[5] != null ? laAccount[5].toString() : null);
				loAccountDto.setAmount(laAccount[6] != null ? Double.parseDouble(laAccount[6].toString()) : null);
				loAccountDto.setOwner(loOwnerDto);
			}
		}
		return loAccountDto;
	}

	@Transactional
	@Modifying
	@Override
	public String modifcaAccountAmount(Integer tiIdAccount, Double tdAmount) {
		System.out.println("AccountDao -> modifcaAccountAmount()");
		String lsResult = null;
		Query query = entityManager.createNativeQuery(
				"update account a set a.amount = :am where a.account = " + tiIdAccount);
		query.setParameter("am", tdAmount);
		query.executeUpdate();
		System.out.println(query.getFirstResult());
		lsResult = "OK";
		
		return lsResult;
	}

	@Override
	public List<TransactionDto> transacciones() {
		List<TransactionDto> laTransactionDtos = new ArrayList<TransactionDto>();
		TransactionDto loTransactionDto = null;
		AccountDto loToAccountDto = null;
		AccountDto loFromAccountDto = null;
		Query query = entityManager.createNativeQuery(
				"select  t.idTransactions, a.idAccount ,a.account, b.idAccount as id,  b.account as acc,  t.amount, t.sentAt  from TRANSACTION t inner join ACCOUNT a on a.idAccount = t.idAccount   inner join ACCOUNT b on  b.idAccount = t.idFromAccount");
		System.out.println(query.toString());
		List<Object[]> list = query.getResultList();
		if (list != null) {
			for (Object[] laFromAccount : list) {
				loTransactionDto = new TransactionDto();
				loToAccountDto = new AccountDto();
				loFromAccountDto = new AccountDto();
				
				loTransactionDto.setIdTransactions(laFromAccount[0] != null ? Integer.parseInt(laFromAccount[0].toString()) : null);
				loFromAccountDto.setIdAccount(laFromAccount[1] != null ? Integer.parseInt(laFromAccount[1].toString()) : null);
				loFromAccountDto.setAccount(laFromAccount[2] != null ? Integer.parseInt(laFromAccount[2].toString()) : null);
				loToAccountDto.setIdAccount(laFromAccount[3] != null ? Integer.parseInt(laFromAccount[3].toString()) : null);
				loToAccountDto.setAccount(laFromAccount[4] != null ? Integer.parseInt(laFromAccount[4].toString()) : null);
				loTransactionDto.setAmount(laFromAccount[5] != null ? Double.parseDouble(laFromAccount[5].toString()) : null);
				loTransactionDto.setSentAt(laFromAccount[6] != null ? laFromAccount[6].toString() : null);
				
				loTransactionDto.setIdFromAccount(loFromAccountDto);
				loTransactionDto.setIdAccount(loToAccountDto);
				
				laTransactionDtos.add(loTransactionDto);
			}
		}
		return laTransactionDtos;
	}

	@Override
	public List<TransactionDto> transaccionesByFromAccount(Integer tiAccount) {
		List<TransactionDto> laTransactionDtos = new ArrayList<TransactionDto>();
		TransactionDto loTransactionDto = null;
		AccountDto loToAccountDto = null;
		AccountDto loFromAccountDto = null;
		Query query = entityManager.createNativeQuery(
				"select  t.idTransactions, a.idAccount ,a.account, b.idAccount as id,  b.account as acc,  t.amount, t.sentAt  from TRANSACTION t inner join ACCOUNT a on a.idAccount = t.idAccount   inner join ACCOUNT b on  b.idAccount = t.idFromAccount where a.account = :formAcc");
		query.setParameter("formAcc", tiAccount);
		System.out.println(query.toString());
		List<Object[]> list = query.getResultList();
		if (list != null) {
			for (Object[] laFromAccount : list) {
				loTransactionDto = new TransactionDto();
				loToAccountDto = new AccountDto();
				loFromAccountDto = new AccountDto();
				
				loTransactionDto.setIdTransactions(laFromAccount[0] != null ? Integer.parseInt(laFromAccount[0].toString()) : null);
				loFromAccountDto.setIdAccount(laFromAccount[1] != null ? Integer.parseInt(laFromAccount[1].toString()) : null);
				loFromAccountDto.setAccount(laFromAccount[2] != null ? Integer.parseInt(laFromAccount[2].toString()) : null);
				loToAccountDto.setIdAccount(laFromAccount[3] != null ? Integer.parseInt(laFromAccount[3].toString()) : null);
				loToAccountDto.setAccount(laFromAccount[4] != null ? Integer.parseInt(laFromAccount[4].toString()) : null);
				loTransactionDto.setAmount(laFromAccount[5] != null ? Double.parseDouble(laFromAccount[5].toString()) : null);
				loTransactionDto.setSentAt(laFromAccount[6] != null ? laFromAccount[6].toString() : null);
				
				loTransactionDto.setIdFromAccount(loFromAccountDto);
				loTransactionDto.setIdAccount(loToAccountDto);
				
				laTransactionDtos.add(loTransactionDto);
			}
		}
		return laTransactionDtos;
	}

	@Override
	public List<TransactionDto> transaccionesByToAccount(Integer tiAccount) {
		List<TransactionDto> laTransactionDtos = new ArrayList<TransactionDto>();
		TransactionDto loTransactionDto = null;
		AccountDto loToAccountDto = null;
		AccountDto loFromAccountDto = null;
		Query query = entityManager.createNativeQuery(
				"select  t.idTransactions, a.idAccount ,a.account, b.idAccount as id,  b.account as acc,  t.amount, t.sentAt  from TRANSACTION t inner join ACCOUNT a on a.idAccount = t.idAccount   inner join ACCOUNT b on  b.idAccount = t.idFromAccount where b.account = :toAcc");
		query.setParameter("toAcc", tiAccount);
		System.out.println(query.toString());
		List<Object[]> list = query.getResultList();
		if (list != null) {
			for (Object[] laFromAccount : list) {
				loTransactionDto = new TransactionDto();
				loToAccountDto = new AccountDto();
				loFromAccountDto = new AccountDto();
				
				loTransactionDto.setIdTransactions(laFromAccount[0] != null ? Integer.parseInt(laFromAccount[0].toString()) : null);
				loFromAccountDto.setIdAccount(laFromAccount[1] != null ? Integer.parseInt(laFromAccount[1].toString()) : null);
				loFromAccountDto.setAccount(laFromAccount[2] != null ? Integer.parseInt(laFromAccount[2].toString()) : null);
				loToAccountDto.setIdAccount(laFromAccount[3] != null ? Integer.parseInt(laFromAccount[3].toString()) : null);
				loToAccountDto.setAccount(laFromAccount[4] != null ? Integer.parseInt(laFromAccount[4].toString()) : null);
				loTransactionDto.setAmount(laFromAccount[5] != null ? Double.parseDouble(laFromAccount[5].toString()) : null);
				loTransactionDto.setSentAt(laFromAccount[6] != null ? laFromAccount[6].toString() : null);
				
				loTransactionDto.setIdFromAccount(loFromAccountDto);
				loTransactionDto.setIdAccount(loToAccountDto);
				
				laTransactionDtos.add(loTransactionDto);
			}
		}
		return laTransactionDtos;
	}
}
