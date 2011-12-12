package com.sa.mt.utils;

import com.sa.mt.options.domain.ReportType;
import org.junit.Test;

import java.util.Date;

import static com.sa.mt.utils.DateUtils.getDate;
import static org.junit.Assert.assertEquals;

public class DateRangeTest {

    @Test
    public void shouldProvideStartAndEndDateForWeeklyReport() {
        Date someDate = getDate("15-Nov-2011");
        DateRange dateRange = new DateRange(someDate, ReportType.WEEKLY);
        assertEquals(getDate("14-Nov-2011"), dateRange.getStartDate());
        assertEquals(getDate("18-Nov-2011"), dateRange.getEndDate());
    }
}
