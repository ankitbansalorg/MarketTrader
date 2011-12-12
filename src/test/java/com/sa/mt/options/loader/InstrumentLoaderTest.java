package com.sa.mt.options.loader;

import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.options.domain.OptionType.PUT;
import static com.sa.mt.utils.DateUtils.getDate;
import static org.mockito.Matchers.argThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.ArrayUtils;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.sa.mt.options.domain.ExpiryDate;
import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.repository.ExpiryDatesRepository;
import com.sa.mt.options.repository.InstrumentRepository;


public class InstrumentLoaderTest {

    @Mock
    private InstrumentRepository instrumentRepository;
    
    @Mock
    private ExpiryDatesRepository expiryDatesRepository;
    
    private InstrumentLoader loader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loader = new InstrumentLoader(instrumentRepository, expiryDatesRepository);
    }

    @Test
    public void shouldLoadInstrumentData() {
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        Date date = new Date();
        instruments.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, getDate("27-MAR-2008"))) ;
        when(instrumentRepository.dataExistsForDate(date)).thenReturn(false);
        loader.loadData(instruments);
        verify(instrumentRepository).save(instruments);
    }

     @Test
      public void shouldNotLoadDataWhenAlreadyExists() {
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        Date date = new Date();
        instruments.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, getDate("27-MAR-2008"))) ;
        when(instrumentRepository.dataExistsForDate(date)).thenReturn(true);
        loader.loadData(instruments);
        verify(instrumentRepository, times(0)).save(instruments);
      }

    @Test
      public void shouldNotThrowErrorForEmptyList() {
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        loader.loadData(instruments);
        verify(instrumentRepository, times(0)).save(instruments);
      }
    
    @Test
    public void shouldLoadInstrumentExpiryDate() {
        ArrayList<Instrument> instruments = new ArrayList<Instrument>();
        Date date = new Date();
        Date date1 = getDate("27-MAR-2008");
        Date date2 = getDate("27-Feb-2008");
		instruments.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, date1)) ;
		instruments.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, date2)) ;
        loader.loadData(instruments);
        verify(instrumentRepository).save(instruments);
        Matcher<List<ExpiryDate>> matcher = matcherForExpiryDates(expiryDateFor(date1), expiryDateFor(date2));
		verify(expiryDatesRepository).save(argThat(matcher));
    }

	private ExpiryDate expiryDateFor(Date date) {
		return new ExpiryDate(date);
	}

	private Matcher<List<ExpiryDate>> matcherForExpiryDates(final ExpiryDate... dates) {
		Matcher<List<ExpiryDate>> matcher = new BaseMatcher<List<ExpiryDate>>() {
			@Override
			public boolean matches(Object o) {
				
				List<ExpiryDate> expiryDates= (List<ExpiryDate>)o;
				List<ExpiryDate> expected = Arrays.asList(dates);
				return expiryDates.size()==expected.size() && expiryDates.containsAll(expected);
			}
			@Override
			public void describeTo(Description arg0) {
			}
        };
		return matcher;
	}
}
