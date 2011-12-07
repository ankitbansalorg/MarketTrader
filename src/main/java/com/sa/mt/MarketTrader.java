package com.sa.mt;

import com.sa.mt.options.pipeline.DailyAveragePipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MarketTrader {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        DailyAveragePipeline pipeline = (DailyAveragePipeline) context.getBean("dailyAveragePipeline");
        pipeline.execute();
    }
}
