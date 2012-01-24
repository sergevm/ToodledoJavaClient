package com.domaindriven.toodledo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public final class ToodledoTimestamp {

	public static final Date GetLocalDateTime(final long localTimeInSeconds) throws ParseException {
		
		DateFormat gmt_formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
		gmt_formatter.setTimeZone(TimeZone.getTimeZone("GMT"));
		DateFormat local_formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	

		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(localTimeInSeconds * 1000);
							
		String formatted = gmt_formatter.format(calendar.getTime());		
		return local_formatter.parse(formatted);
	}
	
	public static final long GetGMTTimeInSeconds(final Date localDateTime) throws ParseException {
				
		DateFormat local_formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
		DateFormat gmt_formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");	
		gmt_formatter.setTimeZone(TimeZone.getTimeZone("GMT"));

		String localDateTimeAsString = local_formatter.format(localDateTime);

		Date gmtDateTime = gmt_formatter.parse(localDateTimeAsString);
		long calculated = gmtDateTime.getTime() / 1000;

		return calculated;
	}
}
