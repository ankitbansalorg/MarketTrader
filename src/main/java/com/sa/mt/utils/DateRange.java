package com.sa.mt.utils;

import com.sa.mt.options.domain.ReportType;
import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Date;

public class DateRange {
    private Date endDate;
    private Date startDate;

    public DateRange(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DateRange dateRange = (DateRange) o;

        if (endDate != null ? !endDate.equals(dateRange.endDate) : dateRange.endDate != null) return false;
        if (startDate != null ? !startDate.equals(dateRange.startDate) : dateRange.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = endDate != null ? endDate.hashCode() : 0;
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DateRange{" +
                "endDate=" + endDate +
                ", startDate=" + startDate +
                '}';
    }
}
