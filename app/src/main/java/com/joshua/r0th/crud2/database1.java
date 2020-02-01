package com.joshua.r0th.crud2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;



public class database1 extends SQLiteOpenHelper {
    private static final String TAG = SqliteHelper.class.getSimpleName();
    public SQLiteDatabase sqLiteDatabase;

    private static final int VERSION = 1;
    public static final String DBNAME = "JENTIK.db";
    public static final String TABLENAME = "data_jentik";
    private static String db_path = "";
    public static String colID = "id";
    public static String COL_1 = "NomorRumah";
    public static String COL_2 = "JentikDalam";
    public static String COL_3 = "JentikLuar";
    private final Context mycontext;

    public database1(Context context) {
        super(context, DBNAME, null, VERSION);
        this.mycontext = context;
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
        String[] columns = new String[]{colID, COL_1, COL_2, COL_3};
        Cursor cursor = sqLiteDatabase.query(TABLENAME, columns,
                null, null, null, null, null);

        return cursor;
    }


    public Cursor getlistall() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLENAME, null);
        return data;
    }

    // Getting All Countries
    public List ListAllData() {
        List listdata = new ArrayList();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLENAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                adapterdata adapter1 = new adapterdata();
                adapter1.setNomorRumah(cursor.getString(1));
                adapter1.setJentikDalam(cursor.getString(2));
                adapter1.setJentikLuar(cursor.getString(3));
                // Adding country to list
                listdata.add(adapter1);
            } while (cursor.moveToNext());
        }

        // return country list
        return listdata;
    }

    // Deleting single country


    // Updating single country
    public int updateData1(adapterdata adapter) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, adapter.getNomorRumah());
        values.put(COL_2, adapter.getJentikDalam());
        values.put(COL_3, adapter.getJentikLuar());

        // updating row
        return db.update(TABLENAME, values, COL_1 + " = ?",
                new String[]{String.valueOf(adapter.getNomorRumah())});
    }

    // Adding new country
    void addCountry(adapterdata country) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COL_1, country.getNomorRumah()); // Country Name
        values.put(COL_2, country.getJentikDalam()); // Country Population
        values.put(COL_3, country.getJentikLuar()); // Country Population
        // Inserting Row
        db.insert(TABLENAME, null, values);
        db.close(); // Closing database connection
    }

    public Cursor QueryData(String query) {
        return sqLiteDatabase.rawQuery(query, null);

    }

    public void cekdatabasecopas() {
        boolean dbexist = cekdatabase();
        if (dbexist) {
            Log.d("TAG", "database already Exist");
        } else {
            this.getReadableDatabase();

        }
    }

    public void copyDatabase() throws IOException {
        InputStream myinput = mycontext.getAssets().open(DBNAME);
        String outfilename = db_path + DBNAME;
        OutputStream myoutput = new FileOutputStream(outfilename);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myinput.read(buffer)) > 0) {
            myoutput.write(buffer, 0, length);

        }
        myoutput.flush();
        myoutput.close();
        myinput.close();
    }

    public boolean cekdatabase() {
        SQLiteDatabase checkdb = null;
        try {
            String mypath = db_path + DBNAME;
            checkdb = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (checkdb != null) {
                checkdb.close();
            }
            return checkdb != null ? true : false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public void opendatabase(){
        String mypath = db_path+DBNAME;
        sqLiteDatabase=SQLiteDatabase.openDatabase(mypath,null,SQLiteDatabase.OPEN_READWRITE);
    }
    public List<adapterdata> getAllData1(){
        Cursor cursor = null;
        ArrayList<adapterdata> listData = new ArrayList<>();

        try {
            SQLiteDatabase db = this.getWritableDatabase();
            cursor = db.rawQuery("SELECT * FROM data_jentik", null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    adapterdata data = new adapterdata();


                    Log.d(TAG,""+cursor.getString(cursor.getColumnIndex("NomorRumah")));
                    Log.d(TAG,""+cursor.getString(cursor.getColumnIndex("JentikDalam")));
                    Log.d(TAG,""+cursor.getString(cursor.getColumnIndex("JentikLuar")));
                    //data.setNomorRumah(cursor.getString(cursor.getColumnIndex("name")));
                    listData.add(data);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null) cursor.close();
        }

        return listData;
    }
}
