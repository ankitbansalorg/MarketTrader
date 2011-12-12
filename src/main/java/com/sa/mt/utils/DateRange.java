package com.sa.mt.utils;

import com.sa.mt.options.domain.ReportType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Date;

public class DateRange {

   private Date currentDate;
    private ReportType reportType;
    private Date endDate;
    private Date startDate;

    public DateRange(Date currentDate, ReportType reportType) {
        this.currentDate = currentDate;
        this.reportType = reportType;
        resolveStartAndEndDate();
    }

    private void resolveStartAndEndDate() {
        DateTime dateTime = new DateTime(currentDate);
        this.startDate = dateTime.withDayOfWeek(DateTimeConstants.MONDAY).toDate();
        this.endDate = dateTime.withDayOfWeek(DateTimeConstants.FRIDAY).toDate();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }
}
