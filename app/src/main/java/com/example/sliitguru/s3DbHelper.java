package com.example.sliitguru;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class s3DbHelper extends SQLiteOpenHelper {
    public s3DbHelper(Context context) {
        //have to change db name
        super(context, "sem3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        //have to change table name
        DB.execSQL("create table semester3(n1 TEXT , n2 TEXT, n3 TEXT, n4 TEXT, n5 TEXT, n6 TEXT, n7 TEXT,m1 TEXT, m2 TEXT, m3 TEXT, m4 TEXT, m5 TEXT, m6 TEXT, m7 TEXT,c1 TEXT, c2 TEXT, c3 TEXT, c4 TEXT, c5 TEXT, c6 TEXT, c7 TEXT,gpa TEXT,credit TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists semester3");
    }

    public Boolean insertdata(String n1,String n2,String n3,String n4,String n5,String n6,String n7,String m1,String m2,String m3,String m4,String m5,String m6,String m7,String c1,String c2,String c3,String c4,String c5,String c6,String c7,String gpa,String credit){

        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("n1",n1);
        contentValues.put("n2",n2);
        contentValues.put("n3",n3);
        contentValues.put("n4",n4);
        contentValues.put("n5",n5);
        contentValues.put("n6",n6);
        contentValues.put("n7",n7);
        contentValues.put("m1",m1);
        contentValues.put("m2",m2);
        contentValues.put("m3",m3);
        contentValues.put("m4",m4);
        contentValues.put("m5",m5);
        contentValues.put("m6",m6);
        contentValues.put("m7",m7);
        contentValues.put("c1",c1);
        contentValues.put("c2",c2);
        contentValues.put("c3",c3);
        contentValues.put("c4",c4);
        contentValues.put("c5",c5);
        contentValues.put("c6",c6);
        contentValues.put("c7",c7);
        contentValues.put("gpa",gpa);
        contentValues.put("credit",credit);

        //have to change table name
        long result=DB.insert("semester3",null,contentValues);
        if(result==-1){
            return false;
        }
        else {
            return true;
        }

    }

    public Boolean updatedata(String n1,String n2,String n3,String n4,String n5,String n6,String n7,String m1,String m2,String m3,String m4,String m5,String m6,String m7,String c1,String c2,String c3,String c4,String c5,String c6,String c7,String gpa,String credit){

        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("n1",n1);
        contentValues.put("n2",n2);
        contentValues.put("n3",n3);
        contentValues.put("n4",n4);
        contentValues.put("n5",n5);
        contentValues.put("n6",n6);
        contentValues.put("n7",n7);
        contentValues.put("m1",m1);
        contentValues.put("m2",m2);
        contentValues.put("m3",m3);
        contentValues.put("m4",m4);
        contentValues.put("m5",m5);
        contentValues.put("m6",m6);
        contentValues.put("m7",m7);
        contentValues.put("c1",c1);
        contentValues.put("c2",c2);
        contentValues.put("c3",c3);
        contentValues.put("c4",c4);
        contentValues.put("c5",c5);
        contentValues.put("c6",c6);
        contentValues.put("c7",c7);
        contentValues.put("gpa",gpa);
        contentValues.put("credit",credit);

        //have to change table name
        Cursor cursor=DB.rawQuery("select * from semester3 where n1=?", new String[]{n1});
        if(cursor.getCount()>0){

            //have to change table name
            long result=DB.update("semester3",contentValues,"n1=?",new String[]{n1});
            if(result==-1){
                return false;
            }
            else {
                return true;
            }
        }else{
            return  false;
        }

    }


    //have to change table name
    public Cursor getdata(){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("select * from semester3",null);
        return cursor;

    }
}
