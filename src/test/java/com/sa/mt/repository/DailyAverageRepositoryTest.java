package com.sa.mt.repository;

import com.sa.mt.options.domain.DailyAverage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/resources/spring-config.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class DailyAverageRepositoryTest {

    @Autowired
    private  DailyAverageRepository repository;

     @Test
      public void shouldStoreDailyAverages() {
         List<DailyAverage> dailyAverages = new ArrayList<DailyAverage>();
         repository.save(dailyAverages);
      }
}
