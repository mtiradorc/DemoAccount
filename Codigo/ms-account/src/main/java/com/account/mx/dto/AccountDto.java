package com.account.mx.dto;

public class AccountDto {

	private Integer idAccount;
	private Integer account;
	private Double balance;
	private Double amount;
	private String createdAt;
	private OwnerDto Owner;

	public AccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AccountDto(Integer idAccount, Integer account, Double balance, Double amount, String createdAt,
			OwnerDto owner) {
		super();
		this.idAccount = idAccount;
		this.account = account;
		this.balance = balance;
		this.amount = amount;
		this.createdAt = createdAt;
		Owner = owner;
	}

	public Integer getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(Integer idAccount) {
		this.idAccount = idAccount;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public OwnerDto getOwner() {
		return Owner;
	}

	public void setOwner(OwnerDto owner) {
		Owner = owner;
	}

	@Override
	public String toString() {
		return "AccountDto [idAccount=" + idAccount + ", account=" + account + ", balance=" + balance + ", amount="
				+ amount + ", createdAt=" + createdAt + ", Owner=" + Owner + "]";
	}

}
