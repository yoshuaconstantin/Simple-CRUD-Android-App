package com.joshua.r0th.crud2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;

public class SqliteHelper extends SQLiteOpenHelper {


    //DATABASE NAME
    public static final String DATABASE_NAME = "loopwiki.com";

    //DATABASE VERSION
    public static final int DATABASE_VERSION = 1;


    public static final String TABLE_USERS = "users";


    //TABLE USERS COLUMNS
    //ID COLUMN @primaryKey
    public static final String KEY_ID = "id";

    //COLUMN user name
    public static final String KEY_USER_NAME = "username";

    //COLUMN email
    public static final String KEY_EMAIL = "email";

    //COLUMN password
    public static final String KEY_PASSWORD = "password";

    //COLUMN No telpon
    public static final String KEY_TELP = "notelp";

    //COLUMN RT
    public static final String KEY_RT = "rt";

    //COLUMN RW
    public static final String KEY_RW = "rw";

    //COLUMN NO Rumah
    public static final String KEY_NORMH = "normh";

    //SQL for creating users table
    public static final String SQL_TABLE_USERS = " CREATE TABLE " + TABLE_USERS
            + " ( "
            + KEY_ID + " INTEGER PRIMARY KEY, "
            + KEY_USER_NAME + " TEXT, "
            + KEY_EMAIL + " TEXT, "
            + KEY_PASSWORD + " TEXT,"
            + KEY_TELP + " TEXT,"
            + KEY_RT + " TEXT,"
            + KEY_RW + " TEXT,"
            + KEY_NORMH + " TEXT"
            + " ) ";

    public  SqliteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create Table when oncreate gets called
        sqLiteDatabase.execSQL(SQL_TABLE_USERS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //drop table to create new one if database version updated
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + TABLE_USERS);
    }

    //using this method we can add users to user table
    public void addUser(User user) {

        //get writable database
        SQLiteDatabase db = this.getWritableDatabase();

        //create content values to insert
        ContentValues values = new ContentValues();

        //Put username in  @values
        values.put(KEY_USER_NAME, user.userName);

        //Put email in  @values
        values.put(KEY_EMAIL, user.email);

        //Put password in  @values
        values.put(KEY_PASSWORD, user.password);


        // insert row
        long todo_id = db.insert(TABLE_USERS, null, values);
    }

    public User Authenticate(User user) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{user.email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email
            User user1 = new User(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));

            //Match both passwords check they are same or not
            if (user.password.equalsIgnoreCase(user1.password)) {
                return user1;
            }
        }

        //if user password does not matches or there is no record with that email then return @false
        return null;
    }

    public boolean isEmailExists(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_USERS,// Selecting Table
                new String[]{KEY_ID, KEY_USER_NAME, KEY_EMAIL, KEY_PASSWORD},//Selecting columns want to query
                KEY_EMAIL + "=?",
                new String[]{email},//Where clause
                null, null, null);

        if (cursor != null && cursor.moveToFirst()&& cursor.getCount()>0) {
            //if cursor has value then in user database there is user associated with this given email so return true
            return true;
        }

        //if email does not exist return false
        return false;
    }
    public String getData(String emailnya) {
        Cursor cursor = null;
        StringBuilder empName = new StringBuilder();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT username FROM users where email=?", new String[] {emailnya});
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    empName.append(cursor.getString(cursor.getColumnIndex("username"))+" ");


                }
            }
            return empName.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }

        return null;
    }


    public String getData1(String emailnya) {

        Cursor cursor = null;



        StringBuilder empName = new StringBuilder();
        try {
            String move;
            Bundle b = new Bundle();
            move = b.getString("UserInput");

            SQLiteDatabase db = this.getWritableDatabase();

            cursor = db.rawQuery("SELECT email FROM users where email=?", new String[] {emailnya});
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    empName.append(cursor.getString(cursor.getColumnIndex("email"))+" ");


                }
            }
            return empName.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }

        return null;
    }
    public String getData2(String emailnya) {
        Cursor cursor = null;

        StringBuilder empName = new StringBuilder();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT password FROM users where email=?", new String[] {emailnya});
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {

                    empName.append(cursor.getString(cursor.getColumnIndex("password"))+" ");


                }
            }
            return empName.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) cursor.close();
        }

        return null;
    }
}