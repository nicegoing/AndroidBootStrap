package com.androidbootstrap.data.bean;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.androidbootstrap.db.PersonModel;
import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.squareup.sqldelight.RowMapper;

@AutoValue
public abstract class Person implements PersonModel {
    public abstract String name();

    public abstract String gender();

    public abstract String age();

    @Nullable
    public abstract Address address();


    public static TypeAdapter<Person> typeAdapter(Gson gson) {
        return new AutoValue_Person.GsonTypeAdapter(gson);
    }

    /**
     * 获取Person的实例
     */
    public static final Factory<Person> FACTORY=new Factory<>(new Creator<Person>() {
        @Override
        public Person create(long _id, @NonNull String name, @NonNull String gender, @NonNull String age) {
            return new AutoValue_Person(_id,name,gender,age,null);
        }
    });

    public static final RowMapper<Person> MAPPER = FACTORY.select_allMapper();
}