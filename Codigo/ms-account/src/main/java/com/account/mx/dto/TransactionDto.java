package com.account.mx.dto;

public class TransactionDto {

	private Integer idTransactions;
	private AccountDto idFromAccount;
	private AccountDto idAccount;
	private Double amount;
	private String sentAt;

	public TransactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TransactionDto(Integer idTransactions, AccountDto idFromAccount, AccountDto idAccount, Double amount,
			String sentAt) {
		super();
		this.idTransactions = idTransactions;
		this.idFromAccount = idFromAccount;
		this.idAccount = idAccount;
		this.amount = amount;
		this.sentAt = sentAt;
	}

	public Integer getIdTransactions() {
		return idTransactions;
	}

	public void setIdTransactions(Integer idTransactions) {
		this.idTransactions = idTransactions;
	}

	public AccountDto getIdFromAccount() {
		return idFromAccount;
	}

	public void setIdFromAccount(AccountDto idFromAccount) {
		this.idFromAccount = idFromAccount;
	}

	public AccountDto getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(AccountDto idAccount) {
		this.idAccount = idAccount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getSentAt() {
		return sentAt;
	}

	public void setSentAt(String sentAt) {
		this.sentAt = sentAt;
	}

	@Override
	public String toString() {
		return "TransactionDto [idTransactions=" + idTransactions + ", idFromAccount=" + idFromAccount + ", idAccount="
				+ idAccount + ", amount=" + amount + ", sentAt=" + sentAt + "]";
	}

}
