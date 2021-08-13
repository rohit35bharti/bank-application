package com.example.bank.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Account {

	@Id
	@Column
	private int accountId;
	@Column
	private String holderName;
	@Column
	private double balance;
	@Column
	private String type;
	

	public Account(int accountId, String holderName, double balance, String type) {
		super();
		this.accountId = accountId;
		this.holderName = holderName;
		this.balance = balance;
		this.type = type;
	}

	public Account() {
		super();
	}


	public int getAccountId() {
		return accountId;
	}


	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}


	public String getHolderName() {
		return holderName;
	}


	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}


	public double getBalance() {
		return balance;
	}


	public void setBalance(double balance) {
		this.balance = balance;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", holderName=" + holderName + ", balance=" + balance + ", type="
				+ type + "]";
	}
	
	
}
