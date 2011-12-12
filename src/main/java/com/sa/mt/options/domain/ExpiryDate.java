package com.sa.mt.options.domain;

import java.util.Date;

import com.sa.mt.utils.DateUtils;

public class ExpiryDate {

	private String month;
	private String year;
	private Date expiryDate;

	public ExpiryDate( ) {
	}
	
	public ExpiryDate(String month, String year, Date expiryDate) {
		this.month = month;
		this.year = year;
		this.expiryDate = expiryDate;
	}
	
	public ExpiryDate(Date date) {
		this.month = DateUtils.getMonth(date);
		this.year = DateUtils.getYear(date);
		this.expiryDate = date;
	}

	@Override
	public boolean equals(Object o){
		if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpiryDate that = (ExpiryDate) o;

        if (expiryDate != null ? !expiryDate.equals(that.expiryDate) : that.expiryDate != null) return false;
        if (year != null ? !year.equals(that.year) : that.year != null) return false;
        if (month != null ? !month.equals(that.month) : that.month != null) return false;
        return true;
	}
	
	@Override
	public int hashCode(){
		int result;
        result = year != null ? year.hashCode() : 0;
        result = 31 * result + (month != null ? month.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        return result;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
}
