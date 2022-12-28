package com.example.sliitguru;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import kotlin.text.UStringsKt;

public class todo_DB_Helper extends SQLiteOpenHelper {
    public todo_DB_Helper(@Nullable Context context) {
        super(context, "tododata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create TABLE tododetails(id integer primary key,title TEXT,description TEXT  )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop TABLE if exists tododetails");
        onCreate(DB);
    }


}