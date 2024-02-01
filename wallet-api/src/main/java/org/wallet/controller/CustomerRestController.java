package org.wallet.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wallet.dto.CustomerNames;
import org.wallet.dto.NamesOnly;
import org.wallet.model.Account;
import org.wallet.model.Customer;
import org.wallet.service.ICustomerService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RequestMapping("/bankapi/v1/customer")
@RestController
public class CustomerRestController {
	
	@Autowired
	private ICustomerService customerService;
	
	@GetMapping("/allnames")
	public List<NamesOnly> getAllNames(){
		return customerService.getAllCustomerNames();
	}
	
	
	
	
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccountsByCustomerId(HttpServletRequest request
			//@PathVariable("id")Integer customerId
			){
		
		//Retrieve Customer Object from session
				HttpSession httpSession= request.getSession(false);
				Customer customer= (Customer)httpSession.getAttribute("customer");
				
		if(customerService.getAllAccounts(customer.getCustomerId())!=null) {
			List<Account> accounts= customerService.getAllAccounts(customer.getCustomerId());
			return new ResponseEntity<List<Account>>(accounts, HttpStatus.OK);
		}
		return new ResponseEntity("No Accounts available for Customer Id:" + customer.getCustomerId(),
				HttpStatus.NOT_FOUND);
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<Customer> validateCustomerLogin(@RequestBody Customer customer,
			HttpSession httpSession,HttpServletRequest request){
		//Customer customer2= customerService.findByEmailIdAndCustomerPassword
				//(customer.getEmailId(), customer.getCustomerPassword());
		
		Customer customer2= customerService.validateLogin
				(customer.getEmailId(), customer.getCustomerPassword());
		if(customer2!=null) {
			//create new session Context
			httpSession=request.getSession(true);
			httpSession.setMaxInactiveInterval(60);
			httpSession.setAttribute("customer", customer2);
			
			return new ResponseEntity<Customer>(customer2, HttpStatus.OK);
		}
		return new ResponseEntity("Invalid Login Details. Please Try Again!", HttpStatus.BAD_REQUEST);
	}
	
	
	
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutCustomer(HttpServletRequest request){
		HttpSession httpSession=request.getSession(false);
		httpSession.invalidate();
		return new ResponseEntity<String>("Customer Logout Successfully!",HttpStatus.OK);
	}
	
	
	
	
	@DeleteMapping("/{id}")
	public ResponseEntity deleteCustomer(@PathVariable("id")Integer customerId){
		//if(customerId>0)
			// throw new NullPointerException("Hey Object Is Null!");
		customerService.deleteCustomer(customerId);
		
		return new ResponseEntity(HttpStatus.OK);
	}
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> findCustomer(@PathVariable("id")Integer customerId){
		Customer customer= customerService.findCustomerById(customerId);
		
		if(customer!=null)
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		return new ResponseEntity("Customer Id:"+ customerId +" is Not Found!!", 
				HttpStatus.NOT_FOUND);
	}
	
	
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomers(){
		List<Customer> customers= customerService.getAllCustomers();
		
		if(!customers.isEmpty())
			return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
		return new ResponseEntity("Customer Db is Empty", HttpStatus.NOT_FOUND);
	}
	
	
	
	
	
	
	
	

	@PostMapping
	public ResponseEntity<String> createCustomer(@RequestBody Customer customer){
		
		Customer customer2= customerService.saveCustomer(customer);
		if(customer2!=null)
			return new ResponseEntity<String>("Record Created Successfully", 
					HttpStatus.CREATED);
		
		return new ResponseEntity<String>("Creation Error!", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping
	public ResponseEntity<String> updateCustomer(@RequestBody Customer customer){
		
		Customer customer2= customerService.updateCustomer(customer);
		if(customer2!=null)
			return new ResponseEntity<String>("Record Updated Successfully", 
					HttpStatus.CREATED);
		
		return new ResponseEntity<String>("Creation Error!", HttpStatus.BAD_REQUEST);
	}
}
