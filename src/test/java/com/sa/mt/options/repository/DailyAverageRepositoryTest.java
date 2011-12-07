package com.sa.mt.options.repository;

import com.sa.mt.options.domain.Instrument;
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
import static com.sa.mt.options.repository.DailyAverageRepository.DAILY_AVERAGES;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/testContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class DailyAverageRepositoryTest {

    @Autowired
    private DailyAverageRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(DAILY_AVERAGES);
    }

     @Test
      public void shouldStoreDailyAverages() {
         Instrument dailyAverage = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("2-JAN-2008"), getDate("27-MAR-2008"));
         repository.save(Arrays.asList(dailyAverage));
         List<Instrument> dailyAverages = repository.getAll();
         assertEquals(1, dailyAverages.size());
         assertEquals(dailyAverage, dailyAverages.get(0));
      }

     @Test
      public void shouldCheckWhetherDataExistsForGivenDate() {
         Instrument dailyAverage = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("2-JAN-2008"), getDate("27-MAR-2008"));
         repository.save(Arrays.asList(dailyAverage));
         assertTrue(repository.dataExistsForDate(getDate("2-JAN-2008")));
         assertFalse(repository.dataExistsForDate(getDate("3-JAN-2008")));
      }
}
