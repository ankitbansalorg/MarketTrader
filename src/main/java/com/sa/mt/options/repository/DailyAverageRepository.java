package com.sa.mt.options.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.Mongo;
import com.mongodb.QueryBuilder;
import org.joda.time.DateTime;
import com.sa.mt.options.domain.DailyAverage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.data.document.mongodb.query.Criteria;
import org.springframework.data.document.mongodb.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

import static org.springframework.data.document.mongodb.query.Criteria.where;

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

    public boolean dataExistsForDate(Date date) {
      //  BasicDBObject query = new BasicDBObject();
     //   query.put("date", date);
        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.put("date").is(date);
        System.out.println(queryBuilder.get());
     //   DateTime dateTime = new DateTime(date);
     //   Query query = new Query(Criteria.where("date").gt(dateTime.toDate()));
     //   return mongoTemplate.find(query, DailyAverage.class).size() > 0;
        return false;
    }
}
