package com.ayan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayan.model.Product;

@Repository(value = "productDao")
public class ProductDaoImpl implements ProductDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Product> getAllProducts() {
		Session session = sessionFactory.openSession();
		List<Product> products = session.createCriteria(Product.class).list();
		System.out.println("-----List of all Products-----");
		System.out.println(products);
		// session.flush is used for clear cache in the session
		session.flush();
		session.close();
		return products;
	}

	public Product getProductById(String productId) {
		Session session = sessionFactory.openSession();
		Product product = (Product) session.get(Product.class, productId);
		session.close();
		return product;
	}

	public void deleteProduct(String productId) {
		Session session = sessionFactory.openSession();
		Product product = (Product) session.get(Product.class, productId);
		session.delete(product);
		session.flush();
		session.close();
	}

	public void addProduct(Product product) {
		System.out.println("Saving product in DAO");
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(product);
		session.getTransaction().commit();
		session.close();
	}

	public void editProduct(Product product) {
		Session session = sessionFactory.openSession();
		session.update(product);
		session.flush();
		session.close();
	}

}
