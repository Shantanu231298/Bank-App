package org.wallet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wallet.dto.CustomerNames;
import org.wallet.dto.NamesOnly;
import org.wallet.exception.CustomerIdNotFoundException;
import org.wallet.model.Account;
import org.wallet.model.Address;
import org.wallet.model.Customer;
import org.wallet.repository.AddressRepository;
import org.wallet.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Override
	public Customer saveCustomer(Customer customer) {
		
		//addressRepository.save(customer.getAddress());
		
		return customerRepository.save(customer);
	}

	@Transactional
	@Override
	public Customer updateCustomer(Customer customer) {
		Customer customer2= findCustomerById(customer.getCustomerId());
		Address address=customer2.getAddress();
		//System.out.println(address);
		if(customer.getAddress()!=null) {
				if(customer.getAddress().getAddressId()==0) {
				Address address2 = addressRepository.save(customer.getAddress());
				//customer2.setAddress(address2);
				customer.setAddress(address2);
			}
		}
		Customer customer1= customerRepository.save(customer); //update Query
		
		if(address!=null && customer.getAddress().getAddressId()>0)
		{
			customer1.setAddress(address);
		}
		
		return customer1;
	}

	@Override
	public Customer findCustomerById(int customerId) {
		return customerRepository
				.findById(customerId)
				.orElseThrow(()->
				 new CustomerIdNotFoundException("Customer Id:"+ customerId+" Not Exists!"));
		 	
	}

	@Override
	public List<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Transactional
	@Override
	public void deleteCustomer(int customerId) {
		Customer customer= findCustomerById(customerId);
		if(customer!=null)
			customerRepository.delete(customer);
	}

	@Override
	public Customer findByEmailIdAndCustomerPassword(String emailId, String customerPassword) {
		// TODO Auto-generated method stub
		return customerRepository.findByEmailIdAndCustomerPassword(emailId, customerPassword);
	}

	@Override
	public Customer validateLogin(String email, String custPwd) {
		// TODO Auto-generated method stub
		return customerRepository.validateLogin(email, custPwd);
	}

	@Override
	public List<Account> getAllAccounts(int customerId) {
		Customer customer=findCustomerById(customerId);
		if(customer!=null) {
			return customer.getAccounts();
		}
		return null;
	}

	@Override
	public List<NamesOnly> getAllCustomerNames() {
		System.out.println(customerRepository.getAllCustomerNames());
		return customerRepository.getAllCustomerNames();
	}
	
	

}
