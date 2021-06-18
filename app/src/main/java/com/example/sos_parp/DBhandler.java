package com.example.sos_parp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhandler extends SQLiteOpenHelper {


    public DBhandler(Context context) {
        super(context, "User.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table personal_info(ID TEXT, Name TEXT, Phone TEXT, BGroup TEXT)");
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", "0");
        contentValues.put("Name", "");
        contentValues.put("Phone", "");
        contentValues.put("BGroup", "");
        db.insert("personal_info", null, contentValues);
    }

    public Cursor getDetails() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from personal_info", null);
        return cursor;
    }

    public Boolean updateDetails(String id, String name, String phone, String bgroup) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ID", id);
        contentValues.put("Name", name);
        contentValues.put("Phone", phone);
        contentValues.put("BGroup", bgroup);
        long result = db.update("personal_info", contentValues, "ID==?", new String[]{id});
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean validateLogin(String email, String pass){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from login_details where Email==? and Password==?", new String[] {email,pass});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public boolean insertRecord(String name, String enroll, String dept, String mobile, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Name", name);
        contentValues.put("Enrollment", enroll);
        contentValues.put("Department", dept);
        contentValues.put("Mobile", mobile);
        contentValues.put("Email", email);
        long result = db.insert("student_details", null, contentValues);
        if (result == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean deleteRecord(String enroll) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select *from student_details where Enrollment==?", new String[] {enroll});

        if(cursor.getCount() > 0) {
            long result = db.delete("student_details", "Enrollment==?", new String[] {enroll});
            if ( result == -1) {
                return false;
            }
            else {
                return true;
            }
        } else {
            return false;
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
