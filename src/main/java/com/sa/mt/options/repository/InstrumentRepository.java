package com.sa.mt.options.repository;

import java.util.Date;
import java.util.List;

import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.utils.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.sa.mt.options.domain.Instrument;

@Repository
public class InstrumentRepository {

    public static final String INSTRUMENTS = "instruments";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(List<Instrument> instruments) {
        mongoTemplate.insertList(INSTRUMENTS, instruments);
    }

    public List<Instrument> getAll() {
        return mongoTemplate.getCollection(INSTRUMENTS, Instrument.class);
    }

    public boolean dataExistsForDate(Date date) {
    	DBObject query  = new QueryBuilder().start().put("date").is(date).get();
    	DBObject obj = mongoTemplate.getCollection(INSTRUMENTS).findOne(query);
        return obj != null;
    }

    public List<Instrument> getDataFor(String symbol, InstrumentType instrumentType,
                                       OptionType optionType, DateRange dateRange) {
        return null;
    }
}
