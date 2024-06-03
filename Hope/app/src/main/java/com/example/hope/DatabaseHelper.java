package com.example.hope;

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
        // Update the table schema to include the 'role' column
        MyDatabase.execSQL("create Table userdetails(name TEXT, email TEXT primary key, password TEXT, role TEXT)");

        // Create the activities table
        MyDatabase.execSQL("create Table activities(id INTEGER PRIMARY KEY AUTOINCREMENT, volunteer_id INTEGER, location TEXT, date TEXT, message TEXT)");

        MyDatabase.execSQL("create Table notifications(id INTEGER PRIMARY KEY AUTOINCREMENT, administrator_id INTEGER, message TEXT)");
    }


    @Override
    public void onUpgrade(SQLiteDatabase MyDatabase, int oldVersion, int newVersion) {
        MyDatabase.execSQL("drop table if exists userdetails");
    }

    // Modify the insertData method to accept the role parameter
    public Boolean insertData(String name, String email, String password, String role) {
        MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("role", role); // Insert the role into the database
        long result = MyDatabase.insert("userdetails", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean insertNotification(int administratorId, String message) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("administrator_id", administratorId); // Administrator's ID
        contentValues.put("message", message);
        long result = MyDatabase.insert("notifications", null, contentValues);
        return result != -1;
    }

    @SuppressLint("Range")
    public ArrayList<String> getNotificationsByAdministrator(int administratorId) {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> notificationsList = new ArrayList<>();

        String[] columns = { "message" };
        String selection = "administrator_id = ?";
        String[] selectionArgs = { String.valueOf(administratorId) };

        Cursor cursor = db.query("notifications", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            do {
                String message = cursor.getString(cursor.getColumnIndex("message"));
                notificationsList.add(message);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return notificationsList;
    }

    @SuppressLint("Range")
    public int getAdministratorId() {
        SQLiteDatabase db = this.getReadableDatabase();
        int administratorId = -1; // Default value if not found

        String[] columns = { "id" }; // Assuming "id" is the column containing user IDs
        String selection = "role = ?"; // Assuming "role" is the column containing user roles
        String[] selectionArgs = { "administrator" }; // The role for administrators

        Cursor cursor = db.query("userdetails", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            administratorId = cursor.getInt(cursor.getColumnIndex("id"));
        }

        cursor.close();
        db.close();

        return administratorId;
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

    public boolean insertActivity(int volunteerId, String location, String date, String message) {
        SQLiteDatabase MyDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("volunteer_id", volunteerId); // Volunteer's ID
        contentValues.put("location", location);
        contentValues.put("date", date);
        contentValues.put("message", message);
        long result = MyDatabase.insert("activities", null, contentValues);
        return result != -1;
    }
    @SuppressLint("Range")
    public int getVolunteerIdByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        int volunteerId = -1; // Default value if not found

        String[] columns = { "volunteer_id" };
        String selection = "email = ?";
        String[] selectionArgs = { email };

        Cursor cursor = db.query("volunteers", columns, selection, selectionArgs, null, null, null);

        if (cursor.moveToFirst()) {
            volunteerId = cursor.getInt(cursor.getColumnIndex("volunteer_id"));
        }

        cursor.close();
        db.close();

        return volunteerId;
    }

}
