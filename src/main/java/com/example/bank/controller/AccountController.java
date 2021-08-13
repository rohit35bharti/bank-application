package com.example.bank.controller;



import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bank.model.Account;
import com.example.bank.model.TransactionStatus;
import com.example.bank.service.AccountService;

/**
 * 
 * @author Vaman
 *
 */

//mark class as Controller
@RestController
@RequestMapping("/Account")
public class AccountController {

	// autowire the AccountService class
	@Autowired
	AccountService accountService;

	/**
	 * creating a get mapping that retrieves the detail of a specific Account
	 * 
	 * @param id
	 * value for account id to find  
	 * @return
	 * Account object based on the input Accounts id 
	 */

	@GetMapping("/details/{id}")
	private Account getAccount(@PathVariable("id") int id) {
		return accountService.getAccountById(id);
	}
	
	@GetMapping("/balance/{id}")
	private double getBalance(@PathVariable("id") int id) {
		return accountService.getBalanceById(id);
	}

//creating post mapping that post the Account detail in the database
	@PostMapping("/create")
	private int saveAccount(@RequestBody Account account) {
		Account newAccount = accountService.createAccount(account);
		return newAccount.getAccountId();
	}
	
	@PostMapping("/withdraw")
	private TransactionStatus withdraw(@RequestBody Map<String, String> requestParams) {
		if (!((requestParams.containsKey("amount")) || (requestParams.containsKey("id"))))
			return new TransactionStatus(false, "Invalid request params.");
		double amount =  Double.parseDouble(requestParams.get("amount"));
		int id = Integer.parseInt(requestParams.get("id"));
		
		TransactionStatus status = accountService.withdraw(id, amount);
		return status;
	}

	@PostMapping("/deposit")
	private TransactionStatus deposit(@RequestBody Map<String, String> requestParams) {
		if (!((requestParams.containsKey("amount")) || (requestParams.containsKey("id"))))
			return new TransactionStatus(false, "Invalid request params.");
		double amount =  Double.parseDouble(requestParams.get("amount"));
		int id = Integer.parseInt(requestParams.get("id"));
		
		TransactionStatus status = accountService.depositBalance(id, amount);
		return status;
	}

}



