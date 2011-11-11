package com.sa.mt.options.parser;

import com.sa.mt.exception.ImproperFormatException;
import com.sa.mt.options.domain.DailyAverage;
import com.sa.mt.options.domain.DailyAverageType;
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

    @Test
    public void shouldExtractDailyAverageDataFromCsv() throws IOException {
        DailyAverageCsvParser csvParser = new DailyAverageCsvParser();
        ClassPathResource resource = new ClassPathResource("SampleDailyAverage.csv");
        List<DailyAverage> dailyAverageList = csvParser.parse(resource.getFile());
        DailyAverage dailyAverage = dailyAverageList.get(0);
        assertEquals(new DailyAverage("ZEEL", 340, DailyAverageType.PA), dailyAverage);
    }

    @Test(expected = ImproperFormatException.class)
    public void shouldFailForUnknownCsvFormat() throws IOException {
        DailyAverageCsvParser csvParser = new DailyAverageCsvParser();
        ClassPathResource resource = new ClassPathResource("ImproperDailyAverage.csv");
        csvParser.parse(resource.getFile());
    }
}