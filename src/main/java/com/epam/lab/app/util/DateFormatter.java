package com.epam.lab.app.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateFormatter {
	public static Date format(String date) {
		Date sqlDate = null;
		try {
			java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			sqlDate = new Date(utilDate.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return sqlDate;
	}
}
