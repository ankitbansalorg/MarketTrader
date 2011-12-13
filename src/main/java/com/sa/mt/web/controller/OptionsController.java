package com.sa.mt.web.controller;

import com.sa.mt.options.domain.ExpiryDate;
import com.sa.mt.options.domain.InstrumentType;
import com.sa.mt.options.domain.OptionType;
import com.sa.mt.options.domain.ReportType;
import com.sa.mt.options.repository.ExpiryDatesRepository;
import com.sa.mt.options.repository.InstrumentRepository;
import com.sa.mt.utils.DateUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

import static com.sa.mt.options.domain.InstrumentType.identify;

@Controller
public class OptionsController {
    private InstrumentRepository instrumentRepository;
    private ExpiryDatesRepository expiryDatesRepository;
    private static final String OPTION_VIEW = "/hello";

    @Autowired
    public OptionsController(InstrumentRepository instrumentRepository, ExpiryDatesRepository expiryDatesRepository) {
        this.instrumentRepository = instrumentRepository;
        this.expiryDatesRepository = expiryDatesRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/options/{symbol}/type/{type}/report/{reportType}")
    public ModelAndView displayDataFor(@PathVariable String symbol, @PathVariable String type,
                                       @PathVariable String reportTypeString,@PathVariable int expiry) {
        Date currentDate = DateUtils.currentDate();
        DateTime dateTime = new DateTime(currentDate);
        ExpiryDate expiryDate = expiryDatesRepository.findExpiryDateFor(dateTime.plusMonths(expiry).toDate());
        ReportType reportType = ReportType.identify(reportTypeString);
        instrumentRepository.getDataFor(symbol, identify("OPTION"), OptionType.identify(type),
                reportType.dateRangeFor(currentDate), expiryDate.getExpiryDate());
        ModelAndView modelAndView = new ModelAndView(OPTION_VIEW);
        modelAndView.addObject("presenter", instrumentRepository.getAll());
        return modelAndView;
    }
}
