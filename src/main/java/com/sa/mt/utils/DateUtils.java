package com.sa.mt.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateUtils {
    private static final String DATEFORMAT = "dd-MMM-yyyy";

	private static final SimpleDateFormat monthFormat = new SimpleDateFormat("MMM");
    
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
    	return monthFormat.format(date);
	}

	public static String getYear(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
	    return dateFormat.format(date);
	}

	public static String getNextMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+1);
		return monthFormat.format(calendar.getTime());
	}
}
