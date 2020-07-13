package com.ayan.service;

import com.ayan.model.Cart;
import com.ayan.model.CartItem;

public interface CartItemService {

	void addCartItem(CartItem cartItem);

	void removeCartItem(String cartItemId);

	void removeAllCartItems(Cart cart);

}
