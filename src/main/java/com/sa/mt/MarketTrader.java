package com.sa.mt;

import com.sa.mt.options.downloader.InstrumentsDownloader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MarketTrader {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("src/main/webapp/WEB-INF/applicationContext.xml");
        InstrumentsDownloader instrumentsDownloader = (InstrumentsDownloader) context.getBean("instrumentPipeline");
        instrumentsDownloader.execute();
    }
}
