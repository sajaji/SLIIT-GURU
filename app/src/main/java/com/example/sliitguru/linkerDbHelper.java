package com.example.sliitguru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class linkerDbHelper extends SQLiteOpenHelper {
    public linkerDbHelper(@Nullable Context context) {
        super(context, "linkerdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
    DB.execSQL("create TABLE linkerdetails(id integer primary key,title TEXT, link TEXT, description TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
    DB.execSQL("drop TABLE if exists linkerdetails");
    onCreate(DB);
    }


}
