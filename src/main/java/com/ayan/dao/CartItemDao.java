package com.ayan.dao;

import com.ayan.model.Cart;
import com.ayan.model.CartItem;

public interface CartItemDao {

	public void addCartItem(CartItem cartItem);

	public void removeCartItem(String cartItemId);
	
	public void removeAllCartItems(Cart cart);

}
