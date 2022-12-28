package com.example.sliitguru;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class reminderDbHelper extends SQLiteOpenHelper {

    private static final String TAG="Database helper";

    public reminderDbHelper(@Nullable Context context) {
        super(context,"Reminder.db",null, 21);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table if not exists reminder(id integer primary key autoincrement,title text,detail text,date text,time text )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists reminder");
        onCreate(db);
    }


}
