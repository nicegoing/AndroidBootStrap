package com.androidbootstrap.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.androidbootstrap.constant.Constants;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/9
 * @since 1.0
 */
public class SpHelper {
    private SharedPreferences        sp   = null;
    private SharedPreferences.Editor edit = null;
    private final Context context;


    public SpHelper(Context context) {
        this.context = context;
        this.sp = context.getApplicationContext().getSharedPreferences(Constants.SP_NAME, Context.MODE_PRIVATE);
        edit = sp.edit();
    }

    public void save(@NonNull String key, boolean value) {
        edit.putBoolean(key, value);
        edit.commit();
    }

    public void save(@NonNull String key, float value) {
        edit.putFloat(key, value);
        edit.commit();
    }

    public void save(@NonNull String key, int value) {
        edit.putInt(key, value);
        edit.commit();
    }

    public void save(@NonNull String key, long value) {
        edit.putLong(key, value);
        edit.commit();
    }

    public void save(String key, String value) {
        edit.putString(key, value);
        edit.commit();
    }

    public boolean getBoolean(String key) {
        return sp.getBoolean(key, false);
    }

    public float getFloat(String key) {
        return sp.getFloat(key, 0);
    }

    public int getInteger(String key) {
        return sp.getInt(key, 0);
    }

    public long getLong(String key) {
        return sp.getLong(key, 0);
    }

    public String getString(String key) {
        return sp.getString(key, "");
    }

    public void remove(String key) {
        edit.remove(key);
        edit.commit();
    }

    public void clear() {
        edit.clear();
        edit.commit();
    }

    public boolean contains(String s) {
        return sp.contains(s);
    }
}