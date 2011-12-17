package com.sa.mt.options.repository;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.utils.DateRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class InstrumentRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(List<Instrument> instruments) {
        mongoTemplate.insertAll(instruments);
    }

    public List<Instrument> getAll() {
        return mongoTemplate.findAll(Instrument.class);
    }

    public boolean dataExistsForDate(Date date) {
        Query query = new Query(Criteria.where("date").is(date));
        return mongoTemplate.findOne(query, Instrument.class) != null;
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
        query.sort().on("date", Order.ASCENDING).on("strikePrice", Order.ASCENDING);

        return mongoTemplate.find(query, Instrument.class);
    }
}
