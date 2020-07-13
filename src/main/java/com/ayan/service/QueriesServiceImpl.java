package com.ayan.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayan.dao.QueriesDao;
import com.ayan.model.Queries;

@Service
public class QueriesServiceImpl implements QueriesService {

	@Autowired
	private QueriesDao queryDao;
	
	
	public void addQuery(Queries query) {
		queryDao.addQuery(query);	
	}

}
