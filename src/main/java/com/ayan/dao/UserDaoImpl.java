package com.ayan.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayan.model.User;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	public List<User> getAllUsers() {
		Session session = sessionFactory.openSession();
		List<User> users = session.createCriteria(User.class).list();
		System.out.println(users);
		session.close();
		return users;
	}

	public void deleteUser(String userId) {
		Session session = sessionFactory.openSession();
		User user = (User) session.get(User.class, userId);
		// notSure should be session.delete
		session.saveOrUpdate(user);
		session.flush();
		session.close();
	}

	public void addUser(User user) {
		Session session = sessionFactory.openSession();
		session.save(user);
		session.close();
	}

	public User getUserById(String userId) {
		Session session = sessionFactory.openSession();
		// if we call get method,Record does not exist it will return null
		// if we call load, if the record does not exist it will throw exception
		User user = (User) session.get(User.class, userId);
		session.close();
		return user;
	}

}
