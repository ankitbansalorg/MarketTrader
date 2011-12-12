package com.sa.mt.web.controller;

import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.options.repository.InstrumentRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;

public class OptionsControllerTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    private OptionsController optionsController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        optionsController = new OptionsController(instrumentRepository);
    }

    @Test
    public void shouldDisplayDataForOption() {
        String symbol = "NIFTY";
        String type = "Put";
        String reportType = "Monthly";
        Date expiryDate = new Date();

        optionsController.displayDataFor(symbol, type, reportType);
      //  verify(instrumentRepository).getDataFor(symbol, InstrumentType.OPTION, OptionType.CALL, expiryDate);
    }
}
