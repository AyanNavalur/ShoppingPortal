package com.ayan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ayan.model.Cart;
import com.ayan.model.Customer;
import com.ayan.model.CustomerOrder;
import com.ayan.service.CartService;
import com.ayan.service.CustomerOrderService;

@Controller
public class OrderController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CustomerOrderService customerOrderService;
	
	@RequestMapping("/order/{cartId}")
	public String createOrder(@PathVariable(value = "cartId") String cartId) {
		CustomerOrder customerOrder = new CustomerOrder();
		
		Cart cart = cartService.getCartByCartId(cartId);
		
		customerOrder.setCart(cart);
		
		Customer customer = cart.getCustomer();
		customerOrder.setCustomer(customer);
		
		customerOrder.setShippingAddress(customer.getShippingAddress());
		customerOrder.setBillingAddress(customer.getBillingAddress());
		
		customerOrderService.addCustomerOrder(customerOrder);
		
		return "redirect:/checkout?cartId=" + cartId;
		
	}
}
