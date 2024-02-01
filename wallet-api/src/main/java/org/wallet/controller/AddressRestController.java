package org.wallet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.wallet.model.Address;
import org.wallet.model.Customer;
import org.wallet.service.IAddressService;

@RequestMapping("/bankapi/v1/address")
@RestController
public class AddressRestController {

	@Autowired
	private IAddressService addressService;
	
	@PostMapping
	public ResponseEntity<String> addAddress(@RequestBody Address address){
	 Address address2= addressService.saveAddress(address);
	 
	 if(address2!=null)
			return new ResponseEntity<String>("Record Created Successfully", 
					HttpStatus.CREATED);
		
		return new ResponseEntity<String>("Creation Error!", HttpStatus.BAD_REQUEST);
	}
	
}
