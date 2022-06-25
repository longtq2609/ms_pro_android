package com.example.ms_pro.base.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GsonUtils {

    public static <T> List<T> getCacheList(Class<T> cls, String json) {
        if (!json.isEmpty()) {
            try {
                return new Gson().fromJson(json, new ListOfJson<>(cls));
            } catch (Exception e) {
                return new ArrayList<T>();
            }
        }
        return new ArrayList<T>();
    }

    public static <T> T getCacheObject(Class<T> cls, String json) {
        if (!json.isEmpty()) {
            Type typeToken = TypeToken.get(cls).getType();
            try {
                return new Gson().fromJson(json, typeToken);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;
    }
}
