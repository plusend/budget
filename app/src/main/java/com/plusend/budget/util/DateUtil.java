package com.plusend.budget.util;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<>();

    private static int getInteger(long dateLong, int dateType) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(dateLong);
        return calendar.get(dateType);
    }

    public static int getYear(long dateLong) {
        return getInteger(dateLong, Calendar.YEAR);
    }

    public static int getMonth(long dateLong) {
        return getInteger(dateLong, Calendar.MONTH);
    }

    public static int getDay(long dateLong) {
        return getInteger(dateLong, Calendar.DATE);
    }

    public static int getWeekOfYear(long dateLong) {
        return getInteger(dateLong, Calendar.WEEK_OF_YEAR);
    }

    public static int getWeek(long dateLong) {
        return getInteger(dateLong, Calendar.DAY_OF_WEEK);
    }

    private static DateFormat getDateFormat() {
        DateFormat dateFormat = threadLocal.get();
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        }
        return dateFormat;
    }

    public static String dateToString(long dateTime) {
        DateFormat dateFormat = getDateFormat();
        return dateFormat.format(new Date(dateTime));
    }

    public static long stringToDate(String dateString) {
        long result = 0;
        DateFormat dateFormat = getDateFormat();
        try {
            Date date = dateFormat.parse(dateString);
            result = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
}
