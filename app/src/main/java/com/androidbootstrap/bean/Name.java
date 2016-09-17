package com.androidbootstrap.bean;

import android.support.annotation.Nullable;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
@AutoValue
public abstract class Name {
    @Nullable
    public abstract String name();

    public static  TypeAdapter<Name> typeAdapter(Gson gson) {
        return new AutoValue_Name.GsonTypeAdapter(gson);
    }

}
