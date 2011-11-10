package com.sa.mt.options.parser;

import com.sa.mt.options.domain.DailyAverage;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class DailyAverageCsvParserTest {

    @Test
    public void shouldRetrieveRowsFromCsv() throws IOException {
        DailyAverageCsvParser csvParser = new DailyAverageCsvParser();
        ClassPathResource resource = new ClassPathResource("SampleDailyAverage.csv");
        List<DailyAverage> dailyAverage = csvParser.parse(resource.getFile());
        assertEquals(3, dailyAverage.size());
    }
}