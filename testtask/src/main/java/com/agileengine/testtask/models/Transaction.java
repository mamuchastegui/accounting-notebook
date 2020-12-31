package com.agileengine.testtask.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "transactions")
@Entity
public class Transaction {

	@Id
	private String id;
	@Column(name = "user_id")
	private Integer userId;
	private TransactionType type;
	private Double amount;
	private Date effectiveDate;

	public Transaction() {
	}

	public Transaction(TransactionType type, Double amount) {
		this.type = type;
		this.amount = amount;
	}

	public Double getAmount() {
		return amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getId() {
		return id;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public enum TransactionType {
		debit,
		credit
	}
}
