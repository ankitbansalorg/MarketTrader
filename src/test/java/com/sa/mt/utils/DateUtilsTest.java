package com.sa.mt.utils;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;

public class DateUtilsTest {

   @Test
   public void shouldParseDateFromGivenString() {
       Calendar cal = Calendar.getInstance();
       cal.set(2008, Calendar.NOVEMBER, 3, 0, 0, 0);
       cal.set(Calendar.MILLISECOND, 0);
       assertEquals(cal.getTime(), DateUtils.getDate("3-Nov-2008"));
   }
   
   @Test
   public void shouldGetMonthFromDate(){
	   Date date = DateUtils.getDate("3-Nov-2008");
	   String month = DateUtils.getMonth(date);
	   assertEquals("Nov", month);
   }

   @Test
   public void shouldGetYearFromDate(){
	   Date date = DateUtils.getDate("3-Nov-2008");
	   String year = DateUtils.getYear(date);
	   assertEquals("2008", year);
   }

   @Test
   public void shouldReturnCurrentDateRemovingTime() {
       Date date = new Date();
       Calendar cal = Calendar.getInstance();
       cal.setTime(date);
       cal.set(Calendar.MILLISECOND, 0);
       cal.set(Calendar.HOUR, 0);
       cal.set(Calendar.MINUTE, 0);
       cal.set(Calendar.SECOND, 0);
       cal.set(Calendar.AM_PM, 0);
       assertEquals(cal.getTime(), DateUtils.currentDate());
   }
}
