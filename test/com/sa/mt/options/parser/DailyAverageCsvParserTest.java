package com.sa.mt.options.parser;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DailyAverageCsvParserTest {

    @Test
    public void shouldParseDailyAverageCsvFile() throws IOException {
        DailyAverageCsvParser csvParser = new DailyAverageCsvParser();
        ClassPathResource resource = new ClassPathResource("SampleDailyAverage.csv");
        List<String[]> dailyAverage = csvParser.parse(resource.getFile());
        assertEquals(4, dailyAverage.size());
    }
}