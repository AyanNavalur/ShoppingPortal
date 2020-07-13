package com.ayan.service;

import java.util.List;

import com.ayan.model.Customer;

public interface CustomerService {
	
	void addCustomer(Customer customer);
	
	List<Customer> getAllCustomers();
	
	Customer getCustomerByEmailId(String emailId);
}
