package com.ayan.dao;

import java.util.List;

import com.ayan.model.Customer;

public interface CustomerDao {

	public void addCustomer(Customer customer);

	public List<Customer> getAllCustomers();

	public Customer getCustomerByemailId(String emailId);

}
