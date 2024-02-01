package org.wallet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wallet.model.Address;
import org.wallet.model.Customer;
import org.wallet.repository.AddressRepository;
import org.wallet.repository.CustomerRepository;


@Service
public class AddressServiceImpl implements IAddressService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private AddressRepository addressRepository;

	@Transactional
	@Override
	public Address saveAddress(Address address) {
		Address address1=addressRepository.save(address);
		System.out.println(address1);
		Customer customer= customerRepository.findById(address1.getCustomer().getCustomerId()).get();
		if(customer!=null)
			customer.setAddress(address1);
		
		return address1;
	}

}
