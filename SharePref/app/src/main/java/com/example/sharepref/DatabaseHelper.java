package com.example.sharepref;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase MyDatabase;
    public static final String databaseName = "Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
        MyDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table allusers(name TEXT, email TEXT primary key,password TEXT, phone TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop table if exists allusers");
    }

    public Boolean insertData(String name, String email, String password, String phone) {
        MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("phone", phone);
        long result = MyDatabase.insert("allusers", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean updatePasswordAndPhone(String email, String newPassword, String newPhone) {
        MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("password", newPassword);
        contentValues.put("phone", newPhone);

        int result = MyDatabase.update("allusers", contentValues, "email = ?", new String[]{email});

        return result > 0;
    }

    public Boolean checkEmail(String email) {
        MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from allusers where email= ?", new String[]{email});
        if (cursor.getCount() > 0) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkEmailPassword(String email,String password)
    {
        SQLiteDatabase MyDatabase=this.getWritableDatabase();
        Cursor cursor=MyDatabase.rawQuery("Select * from allusers where email= ? and password= ?",new String[]{email,password});
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
        int result = MyDatabase.delete("allusers", "email = ?", new String[]{email});
        return result > 0;
    }

    public void closeDatabase() {
        if (MyDatabase != null && MyDatabase.isOpen()) {
            MyDatabase.close();
        }
    }

    public Cursor getUserByEmail(String email) {
        MyDatabase = this.getReadableDatabase();
        return MyDatabase.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});
    }
}
