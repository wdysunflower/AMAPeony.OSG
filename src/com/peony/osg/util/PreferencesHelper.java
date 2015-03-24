package com.peony.osg.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by wdynetposa on 2015/3/23.
 */
public class PreferencesHelper {

    private static SharedPreferences appPreferences;
    private static final String appStr = "app_data";

    private static <T> boolean setData(SharedPreferences preferences, String key, T value) {
        try {

            if (value instanceof Boolean)
                preferences.edit().putBoolean(key, (Boolean) value).commit();
            else if (value instanceof Float)
                preferences.edit().putFloat(key, (Float) value).commit();
            else if (value instanceof Integer)
                preferences.edit().putInt(key, (Integer) value).commit();
            else if (value instanceof Long)
                preferences.edit().putLong(key, (Long) value).commit();
            else if (value instanceof String)
                preferences.edit().putString(key, (String) value).commit();
            else
                return false;
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    private static <T> T getData(SharedPreferences preferences, String key, T defaultValue) {
        T result;
        if (defaultValue instanceof Boolean) {
            Boolean data = preferences.getBoolean(key, (Boolean) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof Float) {
            Float data = preferences.getFloat(key, (Float) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof Integer) {
            Integer data = preferences.getInt(key, (Integer) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof Long) {
            Long data = preferences.getLong(key, (Long) defaultValue);
            result = (T) data;
        } else if (defaultValue instanceof String) {
            String data = preferences.getString(key, (String) defaultValue);
            result = (T) data;
        } else
            return null;
        return result;
    }

    /*
     * AppData
     */
    private static SharedPreferences getAppPreferences(Context context) {
        if (appPreferences == null) {
            appPreferences = context.getSharedPreferences(appStr, 0);
        }
        return appPreferences;
    }

    public static <T> boolean setAppData(Context context, String key, T value) {
        return setData(getAppPreferences(context), key, value);
    }

    public static <T> T getAppData(Context context, String key, T value) {
        return getData(getAppPreferences(context), key, value);
    }
}
