package com.sa.mt.options.domain;

import org.junit.Test;

import static com.sa.mt.options.domain.ReportType.identify;
import static org.junit.Assert.assertSame;

public class ReportTypeTest {

    @Test
    public void shouldIdentifyReportType() {
       assertSame(ReportType.MONTHLY, identify("Monthly"));
       assertSame(ReportType.QUARTERLY, identify("Quarterly"));
       assertSame(ReportType.YEARLY, identify("Yearly"));
       assertSame(ReportType.WEEKLY, identify("Weekly"));
}

}

