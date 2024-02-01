package org.wallet.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.wallet.controller.CustomerRestController;
import org.wallet.model.Address;
import org.wallet.model.Customer;
import org.wallet.repository.CustomerRepository;
import org.wallet.service.CustomerServiceImpl;
import org.wallet.service.ICustomerService;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CustomerRestController.class)
class CustomerRestControllerTest {

	@Mock
	private CustomerRepository customerRepository;
	

	@MockBean
	private CustomerServiceImpl customerService;
	
	@Autowired
	private MockMvc mockMvc; //mocking mvc framework
	
	@Autowired
	private ObjectMapper objectMapper;
	

	@InjectMocks
	CustomerRestController customerRestController;
	
	@BeforeEach
	public void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createCustomerMethod_Test() throws JsonProcessingException, Exception {
		Customer customer=new Customer(1, "Tom", "Jerry", 23000, LocalDate.now(), "tom@gmail.com",
				"tom@1234","312312321", new Address("South Street", "7th Floor", "Chennai", 
						"TamilNadu", "231231"));
		
		//Mockito.when(customerRepository.save(customer)).thenReturn(customer);
		Mockito.when(customerService.saveCustomer(customer)).thenReturn(customer);
		
		mockMvc.perform(post("/bankapi/v1/customer")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(customer)))
				//.andExpect(status().isCreated())
				.andExpect(status().isBadRequest())
				.andDo(print());
	}

}
