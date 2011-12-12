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
            cal.setTimeZone(TimeZone.getDefault());
            return cal.getTime();
        } catch (ParseException e) {
            throw new RuntimeException("Date String"+dateString+" is not in correct format: "+ DATEFORMAT, e);
        }
    }
    
    public static String getMonth(Date date) {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("MMM");
    	return dateFormat.format(date);
	}

	public static String getYear(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	    return dateFormat.format(date);
	}
}
