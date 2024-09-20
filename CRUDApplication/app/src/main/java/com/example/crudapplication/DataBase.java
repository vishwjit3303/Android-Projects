package com.example.crudapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBase extends SQLiteOpenHelper {
    public DataBase( Context context) {
        super(context, "Crud_Database.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sdb) {
        sdb.execSQL("CREATE TABLE Userdetails (name TEXT Primary key, contact TEXT,dob TEXT, city text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sdb, int i, int i1) {
        sdb.execSQL("DROP TABLE IF EXISTS Userdetails");
        onCreate(sdb);
    }

    public Boolean insertUserData(String name, String contact, String dob, String city){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("city",city);
        long result = DB.insert("Userdetails", null, contentValues);
        return  result !=-1;
    }

    public Boolean updateUserData(String name, String contact, String dob, String city){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("city",city);

        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE name = ?", new String[]{name});

        if(cursor.getCount()>0){
            long result = DB.update("Userdetails",  contentValues, "name=?",new String[]{name});
            return  result !=-1;
        } else {
            return  false;
        }

    }

    public Boolean deleteUserData(String name){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = DB.rawQuery("SELECT * FROM Userdetails WHERE name = ?", new String[]{name});

        if(cursor.getCount()>0){
            long result = DB.delete("Userdetails", "name=?",new String[]{name});
            return  result !=-1;
        } else {
            return  false;
        }

    }

    public Cursor getData() {
        SQLiteDatabase DB = this.getWritableDatabase();
        return DB.rawQuery("SELECT * FROM Userdetails", null);
    }

}
