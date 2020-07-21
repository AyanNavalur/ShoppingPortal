package com.ayan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.ayan.model.Cart;
import com.ayan.model.CartItem;
import com.ayan.model.Customer;
import com.ayan.model.Product;
import com.ayan.model.User;
import com.ayan.service.CartItemService;
import com.ayan.service.CartService;
import com.ayan.service.CustomerService;
import com.ayan.service.ProductService;

@Controller
public class CartItemController {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private CartItemService cartItemService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductService productService;

	public CartService getCartService() {
		return cartService;
	}

	public void setCartService(CartService cartService) {
		this.cartService = cartService;
	}

	public CartItemService getCartItemService() {
		return cartItemService;
	}

	public void setCartItemService(CartItemService cartItemService) {
		this.cartItemService = cartItemService;
	}

	public CustomerService getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	public ProductService getProductService() {
		return productService;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/cart/add/{productId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addCartItem(@PathVariable(value = "productId") String productId) {
//		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		String emailId = user.getEmailId();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String emailId = auth.getName();
		Customer customer = customerService.getCustomerByEmailId(emailId);
		System.out.println("Cutomer: " + customer.getUsers().getEmailId());
		Cart cart = customer.getCart();
		System.out.println(cart);
		List<CartItem> cartItems = cart.getCartItem();
		Product product = productService.getProductById(productId);
		
		// if product already exists in cart
		for(int i=0; i<cartItems.size(); i++) {
			CartItem cartItem = cartItems.get(i);
			if(product.getProductId().equals(cartItem.getProduct().getProductId())) {
				cartItem.setQuantity(cartItem.getQuantity() + 1);
				cartItem.setPrice(cartItem.getQuantity() * cartItem.getProduct().getProductPrice());
				cartItemService.addCartItem(cartItem);
				return;
			}
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setQuantity(1);
		cartItem.setPrice(product.getProductPrice());
		cartItem.setProduct(product);
		cartItem.setCart(cart);
		cartItemService.addCartItem(cartItem);
	}
	
	@RequestMapping("/cart/removeCartItem/{cartItemId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeCartItem(@PathVariable(value = "cartItemId") String cartItemId) {
		cartItemService.removeCartItem(cartItemId);
	}
	
	@RequestMapping("/cart/removeAllItems/{cartId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeAllCartItems(@PathVariable(value = "cartId") String cartId) {
		Cart cart = cartService.getCartByCartId(cartId);
		cartItemService.removeAllCartItems(cart);
	}
}
