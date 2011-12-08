package com.sa.mt.web.controller;

import com.sa.mt.options.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OptionsController {
     private InstrumentRepository instrumentRepository;
    private static final String OPTION_VIEW = "/hello";

    @Autowired
    public OptionsController(InstrumentRepository instrumentRepository) {
        this.instrumentRepository = instrumentRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/options/{symbol}")
    public ModelAndView displayData(@PathVariable String symbol) {
        ModelAndView modelAndView = new ModelAndView(OPTION_VIEW);
        modelAndView.addObject("presenter", new Object());
        return modelAndView;
    }
}
