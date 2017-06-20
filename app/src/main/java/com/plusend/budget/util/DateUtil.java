package com.plusend.budget.util;


import java.util.Calendar;

public class DateUtil {
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
}
