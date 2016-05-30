package in.ashwanik.popularmovie1.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by AshwaniK on 5/30/2016.
 */

public class SharedPreference {
    public SharedPreference() {
    }

    public static void saveStringAsPreference(Context context, String prefFileName, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getStringAsPreference(Context context, String prefFileName, String key, String defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        return pref.getString(key, defaultValue);
    }

    public static void saveIntegerAsPreference(Context context, String prefFileName, String key, int value) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getIntegerAsPreference(Context context, String prefFileName, String key, int defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        return pref.getInt(key, defaultValue);
    }

    public static void saveLongAsPreference(Context context, String prefFileName, String key, long value) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public static long getLongAsPreference(Context context, String prefFileName, String key, long defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        return pref.getLong(key, defaultValue);
    }

    public static void saveBooleanAsPreference(Context context, String prefFileName, String key, boolean value) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBooleanAsPreference(Context context, String prefFileName, String key, boolean defaultValue) {
        SharedPreferences pref = context.getSharedPreferences(prefFileName, 0);
        return pref.getBoolean(key, defaultValue);
    }
}