package com.sa.mt.options.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.sa.mt.options.domain.ExpiryDate;
import com.sa.mt.utils.DateUtils;

import static com.sa.mt.utils.DateUtils.getMonth;
import static com.sa.mt.utils.DateUtils.getNextMonth;
import static com.sa.mt.utils.DateUtils.getYear;

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
		return null!=findExpiryDateFor(expiryDate.getMonth(), expiryDate.getYear());
	}

	public List<ExpiryDate> getAll() {
		return mongoTemplate.getCollection(EXPIRYDATES, ExpiryDate.class);
	}

	public ExpiryDate findExpiryDateFor(Date date) {
		ExpiryDate expiryDateForMonth = findExpiryDateFor(getMonth(date), getYear(date));
		if(date.before(expiryDateForMonth.getExpiryDate())) return expiryDateForMonth;
		else return findExpiryDateFor(getNextMonth(date), getYear(date));
	}
	
	private ExpiryDate findExpiryDateFor(String month, String year){
		Query query = new Query().addCriteria(Criteria.where("month").is(month).and("year").is(year));
		return mongoTemplate.findOne(EXPIRYDATES, query, ExpiryDate.class);
	}
}
