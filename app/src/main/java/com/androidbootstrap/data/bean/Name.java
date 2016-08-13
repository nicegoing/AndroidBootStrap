package com.androidbootstrap.data.bean;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Name {
    public abstract String first();
    public abstract String last();

    public static Name create(String first, String last) {
        return new AutoValue_Name(first, last);
    }

    public static TypeAdapter<Name> typeAdapter(Gson gson) {
        return new AutoValue_Name.GsonTypeAdapter(gson);
    }
}
