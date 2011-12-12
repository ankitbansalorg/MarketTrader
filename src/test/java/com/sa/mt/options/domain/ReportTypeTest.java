package com.sa.mt.options.domain;

import com.sa.mt.utils.DateRange;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;

import static com.sa.mt.options.domain.ReportType.MONTHLY;
import static com.sa.mt.options.domain.ReportType.WEEKLY;
import static com.sa.mt.options.domain.ReportType.identify;
import static com.sa.mt.utils.DateUtils.getDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class ReportTypeTest {

    @Test
    public void shouldIdentifyReportType() {
       assertSame(ReportType.MONTHLY, identify("Monthly"));
       assertSame(ReportType.QUARTERLY, identify("Quarterly"));
       assertSame(ReportType.YEARLY, identify("Yearly"));
       assertSame(WEEKLY, identify("Weekly"));
}

    @Test
    public void shouldProvideDateRangeForWeeklyReport() {
        Date startDate = getDate("14-Nov-2011");
        Date endDate = getDate("18-Nov-2011");
        assertEquals(new DateRange(startDate, endDate), WEEKLY.dateRangeFor(getDate("15-Nov-2011")));
    }

    @Test
    public void shouldProvideDateRangeForMonthlyReport() {
        Date startDate = getDate("01-Nov-2011");
        Date endDate = getDate("30-Nov-2011");
        assertEquals(new DateRange(startDate, endDate), MONTHLY.dateRangeFor(getDate("15-Nov-2011")));
    }
}

