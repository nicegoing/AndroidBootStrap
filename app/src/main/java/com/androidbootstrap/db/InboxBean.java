package com.androidbootstrap.db;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.squareup.sqldelight.RowMapper;

import java.util.Calendar;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/8/15
 * @since 1.0
 */
@AutoValue
public abstract class InboxBean implements InboxBeanModel {
    private static final DateAdapter        DATE_ADAPTER = new DateAdapter();
    public static final  Factory<InboxBean> FACTORY      = new Factory<>(new Creator<InboxBean>() {
        @Override
        public InboxBean create(long _id, @NonNull String title, @NonNull String expected_result, @NonNull String todo, @NonNull Calendar add_time, @NonNull Calendar todo_time, @NonNull String type) {
            return new AutoValue_InboxBean(_id, title, expected_result, todo, add_time, todo_time, type);
        }
    }, DATE_ADAPTER, DATE_ADAPTER);

    public static final RowMapper<InboxBean> MAPPER = FACTORY.select_allMapper();
}
