package com.androidbootstrap.rx;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

/**
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/9
 * @since 1.0
 */
@AutoValue
public abstract class ApiResponse<T> {
    public static final int SUCCESS_CODE = 200;

    public abstract int code();

    public abstract String msg();

    public abstract T result();

    public static <T> TypeAdapter<ApiResponse<T>> typeAdapter(Gson gson, TypeToken<? extends ApiResponse<T>> typeToken) {
        return new AutoValue_ApiResponse.GsonTypeAdapter(gson, typeToken);
    }


    public boolean isSuccess() {
        if (code() == SUCCESS_CODE) {
            return true;
        } else {
            return false;
        }
    }
}
