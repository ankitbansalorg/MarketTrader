package com.sa.mt.options.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.sa.mt.options.domain.Instrument;

@Repository
public class InstrumentRepository {

    public static final String DAILY_AVERAGES = "daily_averages";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(List<Instrument> dailyAverages) {
        mongoTemplate.insertList(DAILY_AVERAGES, dailyAverages);
    }

    public List<Instrument> getAll() {
        return mongoTemplate.getCollection(DAILY_AVERAGES, Instrument.class);
    }

    public boolean dataExistsForDate(Date date) {
    	DBObject query  = new QueryBuilder().start().put("date").is(date).get();
    	DBObject obj = mongoTemplate.getCollection(DAILY_AVERAGES).findOne(query);
        return obj != null;
    }
}
