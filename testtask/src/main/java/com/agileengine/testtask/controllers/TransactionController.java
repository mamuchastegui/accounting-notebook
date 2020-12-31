package com.agileengine.testtask.controllers;

import com.agileengine.testtask.daos.TransactionRepository;
import com.agileengine.testtask.models.Account;
import com.agileengine.testtask.models.Transaction;
import com.agileengine.testtask.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TransactionController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private TransactionRepository transactionRepository;

	@CrossOrigin
	@GetMapping("/transactions")
	public Object getTransactions() {
		List<Transaction> transactions = transactionRepository.findAll();
		if (transactions.size() > 0) {
			return transactions;
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("There's no transactions yet."));
	}

	@CrossOrigin
	@PostMapping("/transactions")
	public ResponseEntity runTransaction(@RequestBody Transaction transaction) {

		if (transaction.getType() == null) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The transaction type can't be null.");
		}
		if (transaction.getAmount() < 0) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The amount must be greater than 0.");
		}

		return accountService.updateAccount(transaction.getAmount(), transaction.getType());
	}

	@CrossOrigin
	@GetMapping("/transactions/{id}")
	public Object getTransactionById(@PathVariable String id) {
		Optional<Transaction> transaction = transactionRepository.findById(id);
		if (transaction.isPresent()) {
			return transaction;
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("The transaction %s doesn't exists.", id));
	}

	@CrossOrigin
	@GetMapping("/accounts/{id}")
	public Object getAccount(@PathVariable Integer id) {
		Optional<Account> account = accountService.getAccount(id);
		if (account.isPresent()) {
			return account;
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("The account %s doesn't exists.", id));
	}

}
