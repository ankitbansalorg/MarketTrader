package com.sa.mt.web.controller;

import com.sa.mt.options.repository.DailyAverageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class OptionsController {
     private DailyAverageRepository dailyAverageRepository;

    @Autowired
    public OptionsController(DailyAverageRepository dailyAverageRepository) {
        this.dailyAverageRepository = dailyAverageRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/options/{symbol}")
    public ModelAndView displayData(@PathVariable String symbol) {
        System.out.println(symbol);
        return null;
    }
}