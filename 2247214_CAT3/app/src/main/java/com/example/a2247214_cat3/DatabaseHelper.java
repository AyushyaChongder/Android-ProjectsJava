package com.example.a2247214_cat3;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName = "MCA";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "MCA", null, 1);
    }

    private static DatabaseHelper instance = null;
    public static DatabaseHelper getInstance(Context context){
        if(instance == null)
            instance = new DatabaseHelper(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase MyDatabase) {
        MyDatabase.execSQL("create Table users(name text, email TEXT primary key, password TEXT, post text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users");
    }

    public Boolean insertData(String name, String email, String password,String post){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("post", post);
        long result = MyDatabase.insert("users", null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Boolean checkEmail(String email){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ?", new String[]{email});

        if(cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }
    public Boolean checkEmailPassword(String email, String password){
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        Cursor cursor = MyDatabase.rawQuery("Select * from users where email = ? and password = ?", new String[]{email, password});

        if (cursor.getCount() > 0) {
            return true;
        }else {
            return false;
        }
    }

    public Cursor getUserData(String email) {
        SQLiteDatabase MyDatabase = this.getReadableDatabase();
        Cursor cursor = MyDatabase.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        return cursor;
    }

    public boolean updateUser(String email, String name, String password, String post) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("password", password);
        contentValues.put("post", post);
        int rowsAffected = db.update("users", contentValues, "email = ?", new String[]{email});
        return rowsAffected > 0; // Returns true if rows were updated
    }


    public boolean deleteUser(String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete("users", "email = ?", new String[]{email});
        return rowsAffected > 0; // Returns true if rows were deleted
    }


}
