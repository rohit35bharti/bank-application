package com.example.bank.service;

import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;

import com.example.bank.model.TransactionStatus;

//defining the business logic
@Service
public class AccountService {
	@Autowired
	AccountRepository accountRepository;



//getting a specific record by using the method findById() of CrudRepository
	public double getBalanceById(int id) {
		Account defaultAccount = accountRepository.findById(id).orElse(null);
		if (defaultAccount != null)
			return defaultAccount.getBalance();
		return -1;
	}
	
	public Account getAccountById(int id) {
		Account defaultAccount = accountRepository.findById(id).orElse(null);
		return defaultAccount;
	}

//saving a specific record by using the method save() of CrudRepository
	public Account createAccount(Account account) {
		accountRepository.save(account);
		return account;
	}

	public TransactionStatus depositBalance(int id, double amount) {
		Account defaultAccount = accountRepository.findById(id).orElse(null);
		if (defaultAccount == null)
			return new TransactionStatus(false, "Account does not exist!");
		defaultAccount.setBalance(defaultAccount.getBalance() + amount);
		accountRepository.save(defaultAccount);
		return new TransactionStatus(true, "Successful");
	}
	
	public TransactionStatus withdraw(int id, double amount) {
		Account defaultAccount = accountRepository.findById(id).orElse(null);
		if (defaultAccount == null)
			return new TransactionStatus(false, "Account does not exist!");
		else if(defaultAccount.getBalance() < amount)
			return new TransactionStatus(false, "Insufficient balance.");
		double remainingBalance = defaultAccount.getBalance() - amount;
		defaultAccount.setBalance(remainingBalance);
		accountRepository.save(defaultAccount);
		return new TransactionStatus(true, "Successful");
	}

}



