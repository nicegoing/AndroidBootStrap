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
public class SpUtil {
    private SharedPreferences        sp   = null;
    private SharedPreferences.Editor edit = null;
    private static Context mContext;

    private static class LazyHolder {
        private static final SpUtil INSTANCE = new SpUtil(mContext);
    }

    public static SpUtil getInstance(Context context) {
        mContext = context;
        return LazyHolder.INSTANCE;
    }

    private SpUtil(Context context) {
        this(context.getApplicationContext().getSharedPreferences(Constants.SP_NAME,
                Context.MODE_PRIVATE));
    }

    private SpUtil(SharedPreferences sp) {
        this.sp = sp;
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