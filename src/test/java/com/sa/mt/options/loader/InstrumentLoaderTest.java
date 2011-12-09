package com.sa.mt.options.loader;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.repository.InstrumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static com.sa.mt.options.domain.OptionType.PUT;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class InstrumentLoaderTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    private InstrumentLoader loader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loader = new InstrumentLoader(instrumentRepository);
    }

    @Test
    public void shouldLoadDailyAverageData() {
        ArrayList<Instrument> dailyAverages = new ArrayList<Instrument>();
        Date date = new Date();
        dailyAverages.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, getDate("27-MAR-2008"))) ;
        when(instrumentRepository.dataExistsForDate(date)).thenReturn(false);
        loader.loadData(dailyAverages);
        verify(instrumentRepository).save(dailyAverages);
    }

     @Test
      public void shouldNotLoadDataWhenAlreadyExists() {
        ArrayList<Instrument> dailyAverages = new ArrayList<Instrument>();
        Date date = new Date();
        dailyAverages.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, getDate("27-MAR-2008"))) ;
        when(instrumentRepository.dataExistsForDate(date)).thenReturn(true);
        loader.loadData(dailyAverages);
        verify(instrumentRepository, times(0)).save(dailyAverages);
      }

    @Test
      public void shouldNotThrowErrorForEmptyList() {
        ArrayList<Instrument> dailyAverages = new ArrayList<Instrument>();
        loader.loadData(dailyAverages);
        verify(instrumentRepository, times(0)).save(dailyAverages);
      }
}
