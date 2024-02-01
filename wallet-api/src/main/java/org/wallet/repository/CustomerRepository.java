package org.wallet.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.wallet.dto.CustomerNames;
import org.wallet.dto.NamesOnly;
import org.wallet.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	
	//@Query("from Customer c where c.emailId=?1 and c.customerPassword=?2")
	@Query("from Customer c where c.emailId=:email and c.customerPassword=:custPwd")
	public Customer validateLogin(@Param("email")String email,@Param("custPwd") String custPwd);
	
	
	//@Query("select c.firstName as firstName,c.lastName as lastName from Customer c")
	@Query("select new org.wallet.dto.NamesOnly(c.firstName as fname,c.lastName as lname) "
			+ "from Customer c")
	public List<NamesOnly> getAllCustomerNames();
	//public List<CustomerNames> getAllCustomerNames();
	
	public Customer findByEmailIdAndCustomerPassword(String emailId,String customerPassword);
	//select * from Customer where emailId=?1 and customerPassword=?2; JPQL
}
