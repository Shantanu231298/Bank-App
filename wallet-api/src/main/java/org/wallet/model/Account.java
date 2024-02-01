package org.wallet.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@SequenceGenerator(name="accountSeq",sequenceName = "accountSeq",
initialValue = 12345)
@Entity
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "accountSeq")
	private long accountNo;
	private LocalDate openingDate;
	@Enumerated(EnumType.STRING)
	private AccountType accountType;
	private double openingBalance;
	private String description;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="customerid")
	private Customer customer;
	
	@JsonIgnore
	@OneToMany(targetEntity = Transaction.class,mappedBy = "account")
	private List<Transaction> transactions;
	
	
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	public long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(long accountNo) {
		this.accountNo = accountNo;
	}
	public LocalDate getOpeningDate() {
		return openingDate;
	}
	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}
	public AccountType getAccountType() {
		return accountType;
	}
	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Account(long accountNo, LocalDate openingDate, AccountType accountType, double openingBalance,
			String description) {
		super();
		this.accountNo = accountNo;
		this.openingDate = openingDate;
		this.accountType = accountType;
		this.openingBalance = openingBalance;
		this.description = description;
	}
	public Account() {
		super();
	}
	
	
	public Account(LocalDate openingDate, AccountType accountType, double openingBalance, String description,
			Customer customer) {
		super();
		this.openingDate = openingDate;
		this.accountType = accountType;
		this.openingBalance = openingBalance;
		this.description = description;
		this.customer = customer;
	}
	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", openingDate=" + openingDate + ", accountType=" + accountType
				+ ", openingBalance=" + openingBalance + ", description=" + description + "]";
	}
	
	

}
