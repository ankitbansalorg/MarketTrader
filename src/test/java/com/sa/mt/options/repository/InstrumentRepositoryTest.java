package com.sa.mt.options.repository;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.utils.DateRange;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.sa.mt.options.domain.InstrumentType.FUTURE;
import static com.sa.mt.options.domain.OptionType.CALL;
import static com.sa.mt.options.domain.OptionType.PUT;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static junit.framework.Assert.assertEquals;
import static com.sa.mt.options.repository.InstrumentRepository.INSTRUMENTS;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertArrayEquals;
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

    @Test
    public void shouldRetrieveDataForGivenParams() {
        Instrument instrument1 = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("2-MAR-2008"), getDate("27-MAR-2008")); //in criteria
        Instrument instrument2 = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("5-MAR-2008"), getDate("26-APR-2008")); // expiry wrong
        Instrument instrument3 = new Instrument("NIFTY", OPTION, 6450, PUT, getDate("2-APR-2008"), getDate("27-MAR-2008")); // date wrong
        Instrument instrument4 = new Instrument("NIFTY", OPTION, 6450, CALL, getDate("2-MAR-2008"), getDate("27-MAR-2008")); // option type wrong
        Instrument instrument5 = new Instrument("NIFTY", OPTION, 6000, PUT, getDate("2-MAR-2008"), getDate("27-MAR-2008")); // in criteria
        Instrument instrument6 = new Instrument("NIFTY", FUTURE, 6000, CALL, getDate("2-MAR-2008"), getDate("27-MAR-2008")); //instrument type wrong
        Instrument instrument7 = new Instrument("ABB", FUTURE, 6000, CALL, getDate("2-MAR-2008"), getDate("27-MAR-2008")); // symbol wrong

        List<Instrument> instruments = Arrays.asList(instrument1, instrument2, instrument3, instrument4,
                instrument5, instrument6, instrument7);
        repository.save(instruments);
        List<Instrument> retrievedInstruments = repository.getDataFor("NIFTY", InstrumentType.OPTION, OptionType.PUT,
                new DateRange(getDate("1-MAR-2008"), getDate("31-MAR-2008")), getDate("27-MAR-2008"));

        List<Instrument> expectedInstruments = Arrays.asList(instrument1, instrument5);
        assertEquals(2, retrievedInstruments.size());
        assertArrayEquals(expectedInstruments.toArray(), retrievedInstruments.toArray());
    }
}
