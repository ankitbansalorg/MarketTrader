package com.sa.mt.web.controller;

import com.sa.mt.options.domain.ExpiryDate;
import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.options.domain.ReportType;
import com.sa.mt.options.repository.ExpiryDatesRepository;
import com.sa.mt.options.repository.InstrumentRepository;
import com.sa.mt.utils.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.sa.mt.utils.DateUtils.currentDate;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class OptionsControllerTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    private OptionsController optionsController;
    @Mock
    private ExpiryDatesRepository expiryDatesRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        optionsController = new OptionsController(instrumentRepository, expiryDatesRepository);
    }

    @Test
    public void shouldDisplayDataForOption() {
        String symbol = "NIFTY";
        String type = "Put";
        ReportType reportType = ReportType.MONTHLY;

        ExpiryDate expiryDate = new ExpiryDate(DateUtils.getDate("05-Nov-2011"));
        when(expiryDatesRepository.findExpiryDateFor(currentDate())).thenReturn(expiryDate);
        optionsController.displayDataFor(symbol, type, reportType.reportType(), 0);
        verify(expiryDatesRepository).findExpiryDateFor(currentDate());

        verify(instrumentRepository).getDataFor(symbol, InstrumentType.OPTION, OptionType.PUT,
                reportType.dateRangeFor(currentDate()), expiryDate.getExpiryDate());
    }
}
