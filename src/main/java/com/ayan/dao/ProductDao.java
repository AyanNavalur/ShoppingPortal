package com.ayan.dao;

import java.util.List;

import com.ayan.model.Product;

public interface ProductDao {

	public List<Product> getAllProducts();

	public Product getProductById(String productId);

	public void deleteProduct(String productId);

	public void addProduct(Product product);

	public void editProduct(Product product);

}
