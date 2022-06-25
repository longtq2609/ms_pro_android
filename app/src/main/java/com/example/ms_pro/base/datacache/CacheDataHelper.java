package com.example.ms_pro.base.datacache;

import android.content.Context;
import android.text.TextUtils;

import com.example.ms_pro.base.util.CLog;
import com.google.gson.Gson;


/**
 * Created by hoang on 22-Jun-17.
 */

public class CacheDataHelper {
    public static final String TAG = "CacheDataHelper";

    public static String loadCacheData(Context context, String url) {
        if (TextUtils.isEmpty(url))
            return "";
        CLog.d("loadCacheData " + url);
        MyFileMangerHelper myFileMangerHelper = new MyFileMangerHelper();
        String data = "";
        try {
            data = myFileMangerHelper.readFile(context, url);
        } catch (Exception e) {
            data = "";
        }
        if (!TextUtils.isEmpty(data)) {
            CLog.d("\n" + data);
            return data;
        } else {
            CLog.d("\n" + "null");
            return "";
        }
    }

    public static void saveCacheData(Context context, String url, String data) {
        if (TextUtils.isEmpty(url))
            return;
        MyFileMangerHelper myFileMangerHelper = new MyFileMangerHelper();
        boolean isSuccess = myFileMangerHelper.writeFile(context, url, data);
        String mess = new StringBuilder("saveCacheData: ").append(String.valueOf(isSuccess)).append(" - ").append(url).toString();
        CLog.d(mess);
        CLog.d("\n" + data);
    }

    public static void saveCacheData(Context context, String url, Object dataObject) {
        if (TextUtils.isEmpty(url))
            return;
        String data = new Gson().toJson(dataObject);
        saveCacheData(context, url, data);
    }
}
