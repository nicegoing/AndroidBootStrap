package com.androidbootstrap.data.bean;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

@AutoValue
public abstract class Person {
    public abstract String name();

    public abstract String gender();

    public abstract String age();

    public abstract Address address();


    public static TypeAdapter<Person> typeAdapter(Gson gson) {
        return new AutoValue_Person.GsonTypeAdapter(gson);
    }
}