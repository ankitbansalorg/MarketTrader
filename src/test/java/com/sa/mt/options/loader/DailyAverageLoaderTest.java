package com.sa.mt.options.loader;

import com.sa.mt.options.domain.Instrument;
import com.sa.mt.options.repository.DailyAverageRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Date;

import static com.sa.mt.options.domain.DailyAverageType.PUT;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DailyAverageLoaderTest {

    @Mock
    private DailyAverageRepository dailyAverageRepository;

    private DailyAverageLoader loader;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        loader = new DailyAverageLoader(dailyAverageRepository);
    }

    @Test
    public void shouldLoadDailyAverageData() {
        ArrayList<Instrument> dailyAverages = new ArrayList<Instrument>();
        Date date = new Date();
        dailyAverages.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, getDate("27-MAR-2008"))) ;
        when(dailyAverageRepository.dataExistsForDate(date)).thenReturn(false);
        loader.loadData(dailyAverages);
        verify(dailyAverageRepository).save(dailyAverages);
    }

     @Test
      public void shouldNotLoadDataWhenAlreadyExists() {
        ArrayList<Instrument> dailyAverages = new ArrayList<Instrument>();
        Date date = new Date();
        dailyAverages.add(new Instrument("NIFTY", OPTION, 6450, PUT, date, getDate("27-MAR-2008"))) ;
        when(dailyAverageRepository.dataExistsForDate(date)).thenReturn(true);
        loader.loadData(dailyAverages);
        verify(dailyAverageRepository, times(0)).save(dailyAverages);
      }

    @Test
      public void shouldNotThrowErrorForEmptyList() {
        ArrayList<Instrument> dailyAverages = new ArrayList<Instrument>();
        loader.loadData(dailyAverages);
        verify(dailyAverageRepository, times(0)).save(dailyAverages);
      }
}
