package com.endless.itsmylife.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Preference工具类
 */
public final class PreferencesUtils {

    private static final String DEFAULT_PREF_NAME = "com.endless.itsmylife.default";

    /**
     * 参考 getStringPreference(Context context, String key, String defVal)
     *
     * @param context
     * @param key
     * @return key对应的值字符串
     */
    public static String getStringPreference(Context context, String key) {
        return getStringPreference(context, key, "");
    }

    /**
     * 获取key对应的preference中存储的值
     *
     * @param context
     * @param key
     * @param defVal
     * @return key对应的值字符串
     */
    public static String getStringPreference(Context context, String key, String defVal) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE);
        String value = prefs.getString(key, defVal);
        return value;
    }

    /**
     * 获取key对应的preference中存储的boolean值
     *
     * @param context
     * @param key
     * @param defValue
     * @return key对应的boolean值
     */
    public static boolean getPreferenceBoolean(Context context, String key, boolean defValue) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE);
        boolean value = prefs.getBoolean(key, defValue);
        return value;
    }

    /**
     * 获取key对应的preference中存储的int值
     *
     * @param context
     * @param key
     * @param defValue
     * @return
     */
    public static int getPreferenceInt(Context context, String key, int defValue) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE);
        int value = prefs.getInt(key, defValue);
        return value;
    }

    /**
     * 设置preference中key对应的boolean值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setPreference(Context context, String key, boolean value) {
        SharedPreferences.Editor prefsEditor = context.getSharedPreferences(DEFAULT_PREF_NAME,
                Context.MODE_PRIVATE).edit();
        prefsEditor.putBoolean(key, value);
        prefsEditor.commit();
    }

    /**
     * 设置preference中key对应的String值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setPreference(Context context, String key, String value) {
        SharedPreferences.Editor prefsEditor = context.getSharedPreferences(DEFAULT_PREF_NAME,
                Context.MODE_PRIVATE).edit();
        prefsEditor.putString(key, value);
        prefsEditor.commit();
    }

    /**
     * 设置preference中key对应的int值
     *
     * @param context
     * @param key
     * @param value
     */
    public static void setPreference(Context context, String key, int value) {
        SharedPreferences.Editor prefsEditor = context.getSharedPreferences(DEFAULT_PREF_NAME,
                Context.MODE_PRIVATE).edit();
        prefsEditor.putInt(key, value);
        prefsEditor.commit();
    }

    /**
     * 设置preference中key对应的long值
     *
     * @param context
     * @param key
     * @param value
     */

    public static void setPreference(Context context, String key, long value) {
        SharedPreferences.Editor prefsEditor = context.getSharedPreferences(DEFAULT_PREF_NAME,
                Context.MODE_PRIVATE).edit();
        prefsEditor.putLong(key, value);
        prefsEditor.commit();
    }

    /**
     * 获取key对应的preference中存储的long值
     *
     * @param context
     * @param key
     * @param defaulValue
     * @return
     */
    public static long getPreferenceLong(Context context, String key, long defaulValue) {
        SharedPreferences prefs = context.getSharedPreferences(DEFAULT_PREF_NAME, Context.MODE_PRIVATE);
        long value = prefs.getLong(key, defaulValue);
        return value;
    }

}
