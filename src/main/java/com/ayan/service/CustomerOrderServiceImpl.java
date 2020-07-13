package com.ayan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.dao.CustomerOrderDao;
import com.ayan.model.Cart;
import com.ayan.model.CartItem;
import com.ayan.model.CustomerOrder;

@Service
public class CustomerOrderServiceImpl implements CustomerOrderService {

	@Autowired
	private CustomerOrderDao customerOrderDao;
	
	@Autowired
	private CartService cartService;
	
	public void addCustomerOrder(CustomerOrder customerOrder) {
		customerOrderDao.addCustomerOrder(customerOrder);
	}

	public double getCustomerOrderGrandTotal(String cartId) {
		double grandTotal = 0;
		Cart cart = cartService.getCartByCartId(cartId);
		
		List<CartItem> cartItems = cart.getCartItem();
		
		for(CartItem cartItem: cartItems) {
			grandTotal += cartItem.getPrice();
		}
		
		return grandTotal;
	}
}
