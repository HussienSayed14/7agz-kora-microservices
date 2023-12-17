package com.accountmicroservice.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {

    public static int getCurrentDate() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyMMdd");
        String formattedDate = formatter.format(now);
        return Integer.parseInt(formattedDate);
    }
    public static int getCurrentTime() {
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String formattedTime = formatter.format(now);
        System.out.println(formattedTime);
        return Integer.parseInt(formattedTime);
    }

    public static int tenMinutesFromNow() {
        int tenMinutesFromNow = 10 * 60 * 1000;
        Date time = new Date(System.currentTimeMillis() + tenMinutesFromNow);
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String formattedTime = formatter.format(time);
        return Integer.parseInt(formattedTime);
    }
    public static int hoursFromNow(int hours) {
        int N_HoursFromNow = hours * 60 * 60 * 1000;
        Date time = new Date(System.currentTimeMillis() + N_HoursFromNow);
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String formattedTime = formatter.format(time);
        return Integer.parseInt(formattedTime);
    }
    public static int minutesFromNow(int minutes) {
        int N_MinutesFromNow = minutes * 60 * 1000;
        Date time = new Date(System.currentTimeMillis() + N_MinutesFromNow);
        SimpleDateFormat formatter = new SimpleDateFormat("HHmmss");
        String formattedTime = formatter.format(time);
        return Integer.parseInt(formattedTime);
    }

}
