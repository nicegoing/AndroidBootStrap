package com.androidbootstrap.rx;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

/**
 * result返回的是list的数据，并带有当前页数，总页数
 *
 * @author puhanhui
 * @version 1.0
 * @date 2016/9/14
 * @since 1.0
 */
@AutoValue
public abstract class ListResult<T> {
    public abstract int page();

    public abstract int pageCount();

    public abstract T list();

    public static <T> TypeAdapter<ListResult<T>> typeAdapter(Gson gson, TypeToken<? extends ListResult<T>> typeToken) {
        return new AutoValue_ListResult.GsonTypeAdapter(gson, typeToken);
    }
}
