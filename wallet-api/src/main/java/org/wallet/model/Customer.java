package org.wallet.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
@Entity
public class Customer {
	@Id
	@GeneratedValue
	private int customerId;
	private String firstName;
	private String lastName;
	private double registrationFees;
	private LocalDate registrationDate;
	
	@Column(unique = true)
	private String emailId;
	private String customerPassword;
	private String mobileNo;
	
	
	@OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name="addressid")
	private Address address;
	
	@JsonIgnore
	@OneToMany(targetEntity = Account.class,mappedBy = "customer")
	private List<Account> accounts=new ArrayList<>();
	
	
	
	public List<Account> getAccounts() {
		return accounts;
	}
	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getCustomerPassword() {
		return customerPassword;
	}
	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public double getRegistrationFees() {
		return registrationFees;
	}
	public void setRegistrationFees(double registrationFees) {
		this.registrationFees = registrationFees;
	}
	public LocalDate getRegistrationDate() {
		return registrationDate;
	}
	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}
	public Customer(int customerId, String firstName, String lastName, double registrationFees,
			LocalDate registrationDate) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationFees = registrationFees;
		this.registrationDate = registrationDate;
	}
	
	
	
	public Customer() {
		super();
	}
	
	
	public Customer(int customerId, String firstName, String lastName, double registrationFees,
			LocalDate registrationDate, String emailId, String customerPassword) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationFees = registrationFees;
		this.registrationDate = registrationDate;
		this.emailId = emailId;
		this.customerPassword = customerPassword;
	}
	
	
	public Customer(int customerId, String firstName, String lastName, double registrationFees,
			LocalDate registrationDate, String emailId, String customerPassword, String mobileNo, Address address) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationFees = registrationFees;
		this.registrationDate = registrationDate;
		this.emailId = emailId;
		this.customerPassword = customerPassword;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	
	
	public Customer(String firstName, String lastName, double registrationFees, LocalDate registrationDate,
			String emailId, String customerPassword, String mobileNo, Address address) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.registrationFees = registrationFees;
		this.registrationDate = registrationDate;
		this.emailId = emailId;
		this.customerPassword = customerPassword;
		this.mobileNo = mobileNo;
		this.address = address;
	}
	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", registrationFees=" + registrationFees + ", registrationDate=" + registrationDate + ", emailId="
				+ emailId + ", customerPassword=" + customerPassword + ", mobileNo=" + mobileNo + ", address=" + address
				+ "]";
	}
	
	
	

}
