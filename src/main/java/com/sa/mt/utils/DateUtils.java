package com.sa.mt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private static final String DATEFORMAT = "dd-MMM-yyyy";

    public static Date getDate(String dateString) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATEFORMAT);
        try {
            Date parsedDate = dateFormat.parse(dateString);
            Calendar cal = Calendar.getInstance();
            cal.setTime(parsedDate);
            return cal.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Date String"+dateString+" is not in correct format: "+ DATEFORMAT, e);
        }
    }
}
