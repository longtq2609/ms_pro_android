package com.example.ms_pro.base.util;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

@SuppressLint("SimpleDateFormat")
public class TimeUtil {
    public static String DATE_FORMAT = "dd/MM/yyyy";
    private static String DATE_TIME_FORMAT = "HH:mm dd/MM/yyyy";
    private static String DATE_FORMAT_UTC = "yyyy-MM-dd HH:mm:ss'UTC'";
    public static String TIME_FORMAT = "HH:mm";

    public static String formatLongToDateTime(Long timeLong, boolean isUTC) {
        if (isUTC) {
            return formatUTCToLocalDateTime(timeLong);
        } else {
            return new SimpleDateFormat(DATE_FORMAT).format(new Date(timeLong));
        }
    }

    public static String formatLongToDateTime(Long timeLong, boolean isUTC, String format) {
        if (isUTC) {
            return formatUTCToLocalDateTime(timeLong);
        } else {
            return new SimpleDateFormat(format).format(new Date(timeLong));
        }
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatLongToTime(Long timeLong) {
        SimpleDateFormat oldFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        String dueDateAsNormal = oldFormatter.format(timeLong);
        return dueDateAsNormal;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatLongToTime(Long timeLong, String format) {
        SimpleDateFormat oldFormatter = new SimpleDateFormat(format);
        String dueDateAsNormal = oldFormatter.format(timeLong);
        return dueDateAsNormal;
    }

    @SuppressLint("SimpleDateFormat")
    public static String formatUTCToLocalDateTime(Long timeLong) {
        long value = convertUTCToLocalTime(timeLong);
        SimpleDateFormat oldFormatter = new SimpleDateFormat(DATE_TIME_FORMAT);
        String dueDateAsNormal = oldFormatter.format(value);
        return dueDateAsNormal;
    }

    public static String formatTimeUTCToLocalTime(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String today = sdf.format(new Date());
        today += " " + time;

        SimpleDateFormat df = new SimpleDateFormat(DATE_TIME_FORMAT);
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            date = df.parse(today);
            SimpleDateFormat dfNew = new SimpleDateFormat(TIME_FORMAT);
            dfNew.setTimeZone(TimeZone.getDefault());
            return dfNew.format(date);
        } catch (ParseException e) {
//            e.printStackTrace();
            return time;
        }

    }

    public static long formatLocalTimeToTimeStamp(String dateString) {
        long startDate = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
            Date date = sdf.parse(dateString);
            startDate = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return startDate;
    }

    public static long convertLocalTimeToUTC(long timestampMs) {
        TimeZone localZone = TimeZone.getDefault();
        long offset = localZone.getOffset(timestampMs);
        return timestampMs - offset;
    }

    public static long convertUTCToLocalTime(long timestampMs) {
        TimeZone localZone = TimeZone.getDefault();
//        long offset = localZone.getOffset(timestampMs);
        long offset = TimeUnit.HOURS.toMillis(7);
        return timestampMs + offset;
    }


}