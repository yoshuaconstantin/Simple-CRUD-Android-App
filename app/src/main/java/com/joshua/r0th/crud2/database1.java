package com.joshua.r0th.crud2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class database1 extends SQLiteOpenHelper {

    //nama database

    public static final String DATABASE_NAME = "JENTIK.db";

    //nama table

    public static final String TABLE_NAME = "data_jentik";

    //versi database

    private static final int DATABASE_VERSION = 1;

    //table field

    public static final String COL_1 = "NomorRumah";

    public static final String COL_2 = "JentikDalam";

    public static final String COL_3 = "JentikLuar";




    public database1(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        SQLiteDatabase db = this.getWritableDatabase();

    }



    @Override

    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table data_jentik (NomorRumah integer null ," +

                "JentikDalam integer null," +

                "JentikLuar integer null);");

    }



    @Override

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);

        onCreate(db);

    }



    //metode untuk tambah data

    public boolean insertData(String NomorRumah, String JentikDalam, String JentikLuar) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1,NomorRumah);

        contentValues.put(COL_2,JentikDalam);

        contentValues.put(COL_3,JentikLuar);



        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)

            return false;

        else

            return true;

    }



    //metode untuk mengambil data

    public Cursor getAllData() {

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor res = db.rawQuery("select * from data_jentik", null);

        return res;

    }






    //metode untuk menghapus data

    public int deleteData (String id) {

        SQLiteDatabase db = this.getWritableDatabase();

        return db.delete(TABLE_NAME, "NoRumah = ?", new String[] {id});

    }

    public String getData() {
        Cursor cursor = null;
        StringBuilder empName = new StringBuilder();
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM data_jentik", null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    empName.append(cursor.getString(cursor.getColumnIndex("NomorRumah")) + "\n");
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