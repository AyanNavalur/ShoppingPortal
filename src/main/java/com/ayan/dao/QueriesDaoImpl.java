package com.ayan.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ayan.model.Queries;

@Repository
public class QueriesDaoImpl implements QueriesDao {

	@Autowired
	SessionFactory sessionFactory;
	
	public void addQuery(Queries query) {
		Session session = sessionFactory.openSession();
		session.save(query);
		session.close();
	}

}
