package com.sa.mt.repository;

import com.mongodb.Mongo;
import com.sa.mt.options.domain.DailyAverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DailyAverageRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void save(List<DailyAverage> dailyAverages) {
         mongoTemplate.insertList("daily_average", dailyAverages);
    }
}
