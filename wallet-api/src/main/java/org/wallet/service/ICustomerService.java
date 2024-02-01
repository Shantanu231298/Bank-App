package org.wallet.service;

import java.util.List;

import org.wallet.dto.CustomerNames;
import org.wallet.dto.NamesOnly;
import org.wallet.model.Account;
import org.wallet.model.Customer;

public interface ICustomerService {
	
	public Customer saveCustomer(Customer customer);
	public Customer updateCustomer(Customer customer);
	public Customer findCustomerById(int customerId);
	public List<Customer> getAllCustomers();
	public void deleteCustomer(int customerId);
	public Customer findByEmailIdAndCustomerPassword(String emailId,String customerPassword);
	public Customer validateLogin(String email,String custPwd);
	public List<Account> getAllAccounts(int customerId);
	public List<NamesOnly> getAllCustomerNames();
}
