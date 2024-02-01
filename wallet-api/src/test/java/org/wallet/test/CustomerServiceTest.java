package org.wallet.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.wallet.model.Address;
import org.wallet.model.Customer;
import org.wallet.repository.CustomerRepository;
import org.wallet.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

	@Mock
	CustomerRepository customerRepository;
	
	@InjectMocks
	CustomerServiceImpl customerService;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	@Test
	void createNewCustomerTest() {
		Customer customer=new Customer(1, "Tom", "Jerry", 23000, LocalDate.now(), "tom@gmail.com",
				"tom@1234","312312321", new Address("South Street", "7th Floor", "Chennai", 
						"TamilNadu", "231231"));
		
		//dummy declaration
		Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		
		//actual Method invocation
		Customer customer2= customerService.saveCustomer(customer);
		
		verify(customerRepository, times(1)).save(customer);
		assertEquals(customer2, customer);
		
	}

}
