package com.example.ms_pro.base.util;

import android.content.Context;

import com.blankj.utilcode.util.TimeUtils;
import com.example.ms_pro.R;

import java.util.concurrent.TimeUnit;

public class MyPrettyTime {
    private static final long MINUTE = 60 * 1000L;
    private static final long HOUR = 3600 * 1000L;
    private static final long DAY = 86400 * 1000L;
    private Context context;

    public MyPrettyTime(Context context) {
        this.context = context;
    }

    public String format(long time) {
        return format(time, false);
    }

    public String format(long time, boolean isUTC) {
        long currentTime = TimeUtils.getNowMills();
        if (isUTC) {
            time += TimeUtil.convertUTCToLocalTime(time);
        }
        if (currentTime - time < TimeUnit.MINUTES.toMillis(1)) {
            return context.getString(R.string.report_second_ago);
        } else if (currentTime - time < TimeUnit.HOURS.toMillis(1)) {
            long duration = TimeUnit.MILLISECONDS.toMinutes(currentTime - time);
            return context.getString(R.string.duration_time, String.valueOf(duration), context.getString(R.string.minute));
        } else if (currentTime - time < TimeUnit.DAYS.toMillis(1)) {
            long duration = TimeUnit.MILLISECONDS.toHours(currentTime - time);
            return context.getString(R.string.duration_time, String.valueOf(duration), context.getString(R.string.hour));
        } else {
            return TimeUtil.formatLongToTime(time);
        }
    }

    public String formatLastRepairTime(long time, boolean isUTC) {
        long currentTime = TimeUtils.getNowMills();
        if (isUTC) {
            time += TimeUnit.HOURS.toMillis(7);
        }
        if (currentTime - time < MINUTE) {
            return context.getString(R.string.report_second_ago);
        } else if (currentTime - time < HOUR) {
            long duration = (currentTime - time) / MINUTE;
            return context.getString(R.string.duration_time,
                    String.valueOf(duration), context.getString(R.string.minute));
        } else if (currentTime - time < DAY) {
            long duration = (currentTime - time) / HOUR;
            return context.getString(R.string.duration_time,
                    String.valueOf(duration), context.getString(R.string.hour));
        } else {
            return TimeUtil.formatLongToTime(time, "dd/MM/yyyy");
        }
    }

}
