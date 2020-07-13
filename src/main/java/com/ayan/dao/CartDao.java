package com.ayan.dao;

import java.io.IOException;

import com.ayan.model.Cart;

public interface CartDao {

	public Cart getCartByCartId(String cartId);

	Cart validate(String cartId) throws IOException;
	
	void update(Cart cart);
}
