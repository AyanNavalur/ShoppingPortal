package com.ayan.dao;

import java.util.List;

import com.ayan.model.User;

public interface UserDao {

	public List<User> getAllUsers();
	
	public void deleteUser(String userId);
	
	public void addUser(User user);
	
	public User getUserById(String userId);

}
