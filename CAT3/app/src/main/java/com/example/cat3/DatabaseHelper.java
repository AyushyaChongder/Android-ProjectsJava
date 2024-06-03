package com.example.cat3;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase MyDatabase;
    public static final String databaseName = "cat3.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "cat3.db", null, 1);
        MyDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table userdetails(name TEXT, email TEXT primary key,password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop table if exists userdetails");
    }

    public Boolean insertData(String name, String email, String password) {
        MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result = MyDatabase.insert("userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatePasswordAndPhone(String email, String newPassword) {
        MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        int result = MyDatabase.update("userdetails", contentValues, "email = ?", new String[]{email});

        return result > 0;
    }

    public Boolean checkEmail(String email) {
        MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from userdetails where email= ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email,String password)
    {
        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        Cursor cursor=MyDatabase.rawQuery("Select * from userdetails where email= ? and password= ?",new String[]{email,password});
        if(cursor.getCount() > 0)
        {
            return  true;
        }
        else{
            return  false;
        }
    }

    public Boolean deleteUserByEmail(String email) {
        MyDatabase = this.getWritableDatabase();
        int result = MyDatabase.delete("userdetails", "email = ?", new String[]{email});
        return result > 0;
    }

    public void closeDatabase() {
        if (MyDatabase != null && MyDatabase.isOpen()) {
            MyDatabase.close();
        }
    }

    public Cursor getUserByEmail(String email) {
        MyDatabase = this.getReadableDatabase();
        return MyDatabase.rawQuery("SELECT * FROM userdetails WHERE email = ?", new String[]{email});
    }

    public ArrayList<String> getAllNames() {
        MyDatabase = this.getReadableDatabase();
        ArrayList<String> namesList = new ArrayList<>();

        Cursor cursor = MyDatabase.rawQuery("SELECT name FROM userdetails", null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                namesList.add(name);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return namesList;
    }

}
