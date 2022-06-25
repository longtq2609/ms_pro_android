package com.example.ms_pro.base.util;

import android.util.Log;

import com.example.ms_pro.BuildConfig;

/**
 * Created by anhth on 1/11/18.
 */

public class CLog {
    public static final String TAG = "app.log";

    public static void e(String log) {
        if (BuildConfig.DEBUG)
            Log.e(TAG, log);
    }

    public static void d(String log) {
        if (BuildConfig.DEBUG)
            Log.d(TAG, log);
    }

    public static void w(String log) {
        if (BuildConfig.DEBUG)
            Log.w(TAG, log);
    }

}
