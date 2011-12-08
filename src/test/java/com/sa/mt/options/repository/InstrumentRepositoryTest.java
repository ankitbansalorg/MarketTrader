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

import static com.sa.mt.options.domain.OptionType.PUT;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static junit.framework.Assert.assertEquals;
import static com.sa.mt.options.repository.InstrumentRepository.INSTRUMENTS;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/testContext.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class InstrumentRepositoryTest {

    @Autowired
    private InstrumentRepository repository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void setUp() {
        mongoTemplate.dropCollection(INSTRUMENTS);
    }

     @Test
      public void shouldStoreInstruments() {
         Instrument instrument = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("2-JAN-2008"), getDate("27-MAR-2008"));
         repository.save(Arrays.asList(instrument));
         List<Instrument> instruments = repository.getAll();
         assertEquals(1, instruments.size());
         assertEquals(instrument, instruments.get(0));
      }

     @Test
      public void shouldCheckWhetherDataExistsForGivenDate() {
         Instrument instrument = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("2-JAN-2008"), getDate("27-MAR-2008"));
         repository.save(Arrays.asList(instrument));
         assertTrue(repository.dataExistsForDate(getDate("2-JAN-2008")));
         assertFalse(repository.dataExistsForDate(getDate("3-JAN-2008")));
      }
}
