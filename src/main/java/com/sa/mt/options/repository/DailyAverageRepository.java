package com.sa.mt.options.repository;

import com.mongodb.Mongo;
import com.sa.mt.options.domain.DailyAverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DailyAverageRepository {

    public static final String DAILY_AVERAGES = "daily_averages";

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(List<DailyAverage> dailyAverages) {
         mongoTemplate.insertList(DAILY_AVERAGES, dailyAverages);
    }

    public List<DailyAverage> getAll() {
        return mongoTemplate.getCollection(DAILY_AVERAGES, DailyAverage.class);
    }
}
