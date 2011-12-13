package com.sa.mt.options.repository;

import java.util.Date;
import java.util.List;

import com.mongodb.DBCursor;
import com.sa.mt.options.domain.ExpiryDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;
import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.utils.DateRange;

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
        Query query = new Query(Criteria.where("date").is(date));
        return mongoTemplate.findOne(INSTRUMENTS, query, Instrument.class) != null;
    }

    public List<Instrument> getDataFor(String symbol, InstrumentType instrumentType,
                                       OptionType optionType, DateRange dateRange,
                                       Date expiryDate) {
        Criteria criteria = Criteria.where("symbol").is(symbol).
                            and("instrumentType").is(instrumentType).
                            and("type").is(optionType).
                            and("expiryDate").is(expiryDate).
                            and("date").gte(dateRange.getStartDate()).
                            and("date").lte(dateRange.getEndDate());
        Query query = new Query(criteria);
        return mongoTemplate.find(INSTRUMENTS, query, Instrument.class);
    }
}
