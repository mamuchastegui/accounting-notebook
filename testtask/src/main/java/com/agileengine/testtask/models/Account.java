package com.agileengine.testtask.models;

import javax.persistence.*;

import static com.agileengine.testtask.models.Transaction.TransactionType.debit;

@Table(name = "accounts")
@Entity
public class Account {

	@Id
	@GeneratedValue
	private Integer id;
	@Column(name = "user_id")
	private Integer userId;
	private Double balance;

	@Version
	private Integer version;

	public Account() {
	}

	public Account(Integer id, Integer userId, Double balance) {
		this.id = id;
		this.userId = userId;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public Integer getUserId() {
		return userId;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Transaction.TransactionType transactionType, Double transactionAmount) {
		if (debit.equals(transactionType)) {
			transactionAmount *= -1;
		}
		this.balance = balance + transactionAmount;
	}
}
