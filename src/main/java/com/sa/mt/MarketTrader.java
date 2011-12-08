package com.sa.mt;

import com.sa.mt.options.pipeline.InstrumentPipeline;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MarketTrader {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        InstrumentPipeline pipeline = (InstrumentPipeline) context.getBean("instrumentPipeline");
        pipeline.execute();
    }
}
