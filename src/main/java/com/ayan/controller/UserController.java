package com.ayan.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ayan.model.BillingAddress;
import com.ayan.model.Customer;
import com.ayan.model.ShippingAddress;
import com.ayan.model.User;
import com.ayan.service.CustomerService;

@Controller
public class UserController {
	
	@Autowired
	private CustomerService customerService;

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@RequestMapping(value = "/customer/registration")
	public ModelAndView getRegistrationForm() {
		Customer customer = new Customer();
		User user = new User();
		BillingAddress ba = new BillingAddress();
		ShippingAddress sa = new ShippingAddress();
		customer.setUsers(user);
		customer.setBillingAddress(ba);
		customer.setShippingAddress(sa);
		
		return new ModelAndView("register", "customer", customer);
	}
	
	@RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
	public String registerCustomer(@Valid @ModelAttribute(value = "customer") Customer customer, Model model, BindingResult result) {
		if(result.hasErrors()) {
			return "register";
		}
		customerService.addCustomer(customer);
		model.addAttribute("registrationSuccess", "User registered successfully");
		return "login";
	}
}
