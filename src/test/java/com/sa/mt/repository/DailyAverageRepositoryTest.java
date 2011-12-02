package com.sa.mt.repository;

import com.sa.mt.options.domain.DailyAverage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.Arrays;
import java.util.List;

import static com.sa.mt.options.domain.DailyAverageType.PUT;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static junit.framework.Assert.assertEquals;
import static com.sa.mt.repository.DailyAverageRepository.DAILY_AVERAGES;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class DailyAverageRepositoryTest {

    @Autowired
    private  DailyAverageRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(DAILY_AVERAGES);
    }

     @Test
      public void shouldStoreDailyAverages() {
         DailyAverage dailyAverage = new DailyAverage("NIFTY", OPTION, 6450, PUT, getDate("2-JAN-2008"), getDate("27-MAR-2008"));
         repository.save(Arrays.asList(dailyAverage));
         List<DailyAverage> dailyAverages = repository.getAll();
         assertEquals(1, dailyAverages.size());
         assertEquals(dailyAverage, dailyAverages.get(0));
      }
}
