package com.sa.mt.options.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.sa.mt.options.domain.ExpiryDate;

@Repository
public class ExpiryDatesRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
	
    public static final String EXPIRYDATES = "expirydates";

    
	public void save(List<ExpiryDate> expiryDates) {
		mongoTemplate.insertList(EXPIRYDATES,expiryDates);
	}

	public List<ExpiryDate> getAll() {
		return mongoTemplate.getCollection(EXPIRYDATES, ExpiryDate.class);
	}
}
