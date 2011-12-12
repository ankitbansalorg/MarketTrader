package com.sa.mt.options.repository;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.sa.mt.options.domain.ExpiryDate;
import com.sa.mt.utils.DateUtils;

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

	public ExpiryDate findExpiryDateFor(Date date) {
		return findExpiryDateFor(DateUtils.getMonth(date), DateUtils.getYear(date));
	}

	public ExpiryDate findNextExpiryDateFor(Date date) {
		ExpiryDate expiryDateForMonth = findExpiryDateFor(date);
		if(date.before(expiryDateForMonth.getExpiryDate())) return expiryDateForMonth;
		else return findExpiryDateForNextMonth(date);
	}

	private ExpiryDate findExpiryDateForNextMonth(Date date) {
		return findExpiryDateFor(DateUtils.getNextMonth(date), DateUtils.getYear(date));
	}
	
	private ExpiryDate findExpiryDateFor(String month, String year){
		Query query = new Query().addCriteria(Criteria.where("month").is(month).and("year").is(year));
		return mongoTemplate.findOne(EXPIRYDATES, query , ExpiryDate.class);
	}
}
