package com.androidbootstrap.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.androidbootstrap.bean.Person;

public final class DBOpenHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;

    public DBOpenHelper(Context context) {
        super(context, "todo.db", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Person.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
