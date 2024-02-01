package org.wallet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CustomerAccountApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAccountApiApplication.class, args);
	}

}
