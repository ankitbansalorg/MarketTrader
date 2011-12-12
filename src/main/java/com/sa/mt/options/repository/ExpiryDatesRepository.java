package com.sa.mt.options.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.sa.mt.options.domain.ExpiryDate;

@Repository
public class ExpiryDatesRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
	
    public static final String EXPIRYDATES = "expirydates";

    
	public void save(List<ExpiryDate> expiryDates) {
		for (ExpiryDate expiryDate : expiryDates) {
			if(!exists(expiryDate)) mongoTemplate.insert(EXPIRYDATES, expiryDate);
		}
	}

	private boolean exists(ExpiryDate expiryDate) {
		DBObject query = new QueryBuilder().start().put("month").is(expiryDate.getMonth()).put("year").is(expiryDate.getYear()).get();
		Object obj= mongoTemplate.getCollection(EXPIRYDATES).findOne(query );
		return obj!=null;
	}

	public List<ExpiryDate> getAll() {
		return mongoTemplate.getCollection(EXPIRYDATES, ExpiryDate.class);
	}
}
