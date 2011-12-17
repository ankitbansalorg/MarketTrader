package com.sa.mt.options.repository;

import com.sa.mt.options.domain.ExpiryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static com.sa.mt.utils.DateUtils.*;

@Repository
public class ExpiryDatesRepository {

    @Autowired
    private MongoTemplate mongoTemplate;
    
	public void save(List<ExpiryDate> expiryDates) {
		for (ExpiryDate expiryDate : expiryDates) {
			if(!exists(expiryDate)) mongoTemplate.insert(expiryDate);
		}
	}

	private boolean exists(ExpiryDate expiryDate) {
		return null!=findExpiryDateFor(expiryDate.getMonth(), expiryDate.getYear());
	}

	public List<ExpiryDate> getAll() {
		return mongoTemplate.findAll(ExpiryDate.class);
	}

	public ExpiryDate findExpiryDateFor(Date date) {
		ExpiryDate expiryDateForMonth = findExpiryDateFor(getMonth(date), getYear(date));
		if(date.before(expiryDateForMonth.getExpiryDate())) return expiryDateForMonth;
		else return findExpiryDateFor(getNextMonth(date), getYear(date));
	}
	
	private ExpiryDate findExpiryDateFor(String month, String year){
		Query query = new Query().addCriteria(Criteria.where("month").is(month).and("year").is(year));
		return mongoTemplate.findOne(query, ExpiryDate.class);
	}
}
