package com.sa.mt.options.parser;

import com.sa.mt.exception.ImproperFormatException;
import com.sa.mt.options.domain.DailyAverage;
import com.sa.mt.options.domain.DailyAverageType;
import com.sa.mt.utils.DateUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.sa.mt.options.domain.DailyAverageType.CALL;
import static com.sa.mt.options.domain.DailyAverageType.PUT;
import static com.sa.mt.options.domain.InstrumentType.OPTION;
import static com.sa.mt.utils.DateUtils.getDate;
import static org.apache.commons.lang.builder.EqualsBuilder.reflectionEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
        assertTrue(reflectionEquals(new DailyAverage("RPL", OPTION, 200, CALL, 31, 33.95, 31, 33.95, 33.95, 3, 11.69,
                51925, 0, getDate("2-JAN-2008"), getDate("31-JAN-2008")), dailyAverageList.get(0)));
        assertEquals(new DailyAverage("NIFTY", OPTION, 6450, CALL, getDate("2-JAN-2008"), getDate("27-MAR-2008")), dailyAverageList.get(1));
        assertEquals(new DailyAverage("ZEEL", OPTION, 350, PUT, getDate("2-JAN-2008"), getDate("27-MAR-2008")), dailyAverageList.get(2));
    }

    @Test(expected = ImproperFormatException.class)
    public void shouldFailForUnknownCsvFormat() throws IOException {
        DailyAverageCsvParser csvParser = new DailyAverageCsvParser();
        ClassPathResource resource = new ClassPathResource("ImproperDailyAverage.csv");
        csvParser.parse(resource.getFile());
    }
}