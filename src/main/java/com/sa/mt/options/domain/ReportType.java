package com.sa.mt.options.domain;

import com.sa.mt.utils.DateRange;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Date;

public enum ReportType {
    WEEKLY("Weekly") {
        @Override
        public DateRange dateRangeFor(Date date) {
            DateTime dateTime = new DateTime(date);
            Date startDate = dateTime.withDayOfWeek(DateTimeConstants.MONDAY).toDate();
            Date endDate = dateTime.withDayOfWeek(DateTimeConstants.FRIDAY).toDate();
            return new DateRange(startDate, endDate);
        }
    }, MONTHLY("Monthly") {
        @Override
        public DateRange dateRangeFor(Date date) {
            return null;
        }
    }, YEARLY("Yearly") {
        @Override
        public DateRange dateRangeFor(Date date) {
            return null;
        }
    }, QUARTERLY("Quarterly") {
        @Override
        public DateRange dateRangeFor(Date date) {
            return null;
        }
    };

    private String reportType;

    ReportType(String reportType) {
        this.reportType = reportType;
    }

    public static ReportType identify(String reportTypeString) {
        for(ReportType reportType : ReportType.values()) {
            if(reportType.reportType.equals(reportTypeString)) {
                return reportType;
            }
        }
        return null;
    }

    public abstract DateRange dateRangeFor(Date date);
}
