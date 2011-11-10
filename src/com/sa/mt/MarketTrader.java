package com.sa.mt;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class MarketTrader {
    public static void main(String[] args) {
        ApplicationContext context = new GenericXmlApplicationContext("spring-config.xml");
        System.out.println(context.containsBean("mongoTemplate"));
    }
}
