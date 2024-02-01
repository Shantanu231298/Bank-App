package org.wallet.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Transaction {
	@Id
	@GeneratedValue
	private int transactionId;
	private LocalDate transactionDate;
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;
	private double amount;
	private String description;
	@ManyToOne
	@JoinColumn(name="accountno")
	private Account account;
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Transaction(int transactionId, LocalDate transactionDate, TransactionType transactionType, double amount,
			String description, Account account) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
		this.description = description;
		this.account = account;
	}
	
	
	public Transaction(LocalDate transactionDate, TransactionType transactionType, double amount, String description,
			Account account) {
		super();
		this.transactionDate = transactionDate;
		this.transactionType = transactionType;
		this.amount = amount;
		this.description = description;
		this.account = account;
	}
	public Transaction() {
		super();
	}
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionDate=" + transactionDate
				+ ", transactionType=" + transactionType + ", amount=" + amount + ", description=" + description + "]";
	}
	
	
}
