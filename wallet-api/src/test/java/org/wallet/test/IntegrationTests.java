package org.wallet.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.wallet.controller.CustomerRestController;
import org.wallet.model.Address;
import org.wallet.model.Customer;
import org.wallet.repository.CustomerRepository;
import org.wallet.service.CustomerServiceImpl;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class IntegrationTests {

	@LocalServerPort
	private int port;
	
	
	@Autowired
	private TestRestTemplate restTemplate;
	
		
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	
	
	@Test
	public void getAllCustomer_test() {
		String url="http://localhost:"+port+"/bankapi/v1/customer";
		
		//Customer[] customers= restTemplate.getForObject(url, Customer[].class);
		
		restTemplate.delete(url+"/302");
		assertThat(restTemplate.getForObject(url+"/302", Customer.class))
				.isInstanceOf(Customer.class);
	
		//assertThat(customers).extracting(Customer::getFirstName).contains("annie");
	}
	
	
/*	@Test
	void CreateCustomer_Test() {
		String url="http://localhost:"+port+"/bankapi/v1/customer";
		Customer customer=new Customer(901, "kamal", "singh", 56000, LocalDate.now(), "kamal@gmail.com",
				"kamal@1234","34343434", new Address("North Street", "7th Floor", "Chennai", 
						"TamilNadu", "231231"));
		
		ResponseEntity<String> responseEntity=
				restTemplate.postForEntity(url, customer, String.class);
		
		assertEquals("Record Created Successfully", responseEntity.getBody());
		
	}*/

}
