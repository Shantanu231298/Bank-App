package org.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wallet.model.Account;
import org.wallet.model.Customer;
import org.wallet.service.IAccountService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/bankapi/v1/account")
public class AccountRestController {
	
	@Autowired
	private IAccountService accountService;

	
	@PostMapping
	public ResponseEntity<String> createNewAccount(@RequestBody Account account, 
			HttpServletRequest request ){
		
		//Retrieve Customer Object from session
		HttpSession httpSession= request.getSession(false);
		Customer customer= (Customer)httpSession.getAttribute("customer");
		
		account.setCustomer(customer);
		
		Account account2= accountService.saveAccount(account);
		if(account2!=null)
			return new ResponseEntity("New Account Created :" + account.getAccountNo(),
					HttpStatus.OK);
		return new ResponseEntity("Account Creation Error!",HttpStatus.BAD_REQUEST);
	}
}
