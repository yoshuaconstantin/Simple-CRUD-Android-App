package com.joshua.r0th.crud2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class database1 extends SQLiteOpenHelper {
    public SQLiteDatabase sqLiteDatabase;

    private static final int VERSION = 1;
    public static final String DBNAME = "JENTIK.db";
    public static final String TABLENAME = "data_jentik";

    public static String colID = "id";
    public static String COL_1 = "NomorRumah";
    public static String COL_2 = "JentikDalam";
    public static String COL_3 = "JentikLuar";

    public database1(Context context) {
        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLENAME + " (" +
                colID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_1 + " TEXT," + COL_2 + "  TEXT," +
                COL_3 + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLENAME);
        onCreate(db);
    }

    //metode untuk tambah data

    public boolean insertData(String NomorRumah, String JentikDalam, String JentikLuar) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(COL_1, NomorRumah);

        contentValues.put(COL_2, JentikDalam);

        contentValues.put(COL_3, JentikLuar);


        long result = db.insert(TABLENAME, null, contentValues);

        if (result == -1)

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

    public void updateData(int id, String NomorRumah, String JentikDalam, String JentikLuar) {
        String updateData = "UPDATE " + TABLENAME + " SET " + COL_1 + "= '" + NomorRumah + "', " + COL_2 + "= '" + JentikDalam + "'," + COL_3 + "='" + JentikLuar + "' WHERE " + colID + " =" + id;
        this.getWritableDatabase().execSQL(updateData);
    }

    public void deleteData(int id) {
        String deleteData = "DELETE FROM " + TABLENAME + " WHERE id=" + id;
        this.getWritableDatabase().execSQL(deleteData);
    }

    public datamodel getData(int id) {
        datamodel model = null;
        String selectData = "SELECT * FROM " + TABLENAME + " WHERE id=" + String.valueOf(id);
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToFirst()) {
            model = new datamodel(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                    data.getString(data.getColumnIndex(COL_1)), data.getString(data.getColumnIndex(COL_2)), data.getString(data.getColumnIndex(COL_3)));
        }
        return model;
    }

    public List<datamodel> getAll() {
        List<datamodel> model = new ArrayList<>();
        String selectData = "SELECT * FROM " + TABLENAME;
        Cursor data = this.getWritableDatabase().rawQuery(selectData, null);
        if (data.moveToPosition(1)) {
            do {
                model.add(new datamodel(Integer.parseInt(data.getString(data.getColumnIndex(colID))),
                        data.getString(data.getColumnIndex(COL_1)), data.getString(data.getColumnIndex(COL_2)), data.getString(data.getColumnIndex(COL_3))));
            } while (data.moveToNext());
        }
        return model;
    }

    public Cursor queueAll() {
        String[] columns = new String[]{colID,COL_1, COL_2, COL_3};
        Cursor cursor = sqLiteDatabase.query(TABLENAME, columns,
                null, null, null, null, null);

        return cursor;
    }


public Cursor getlistall(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data  = db.rawQuery("SELECT * FROM " + TABLENAME,null);
        return  data;
}
}
