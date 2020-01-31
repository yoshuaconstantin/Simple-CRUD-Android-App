package com.joshua.r0th.crud2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class lihatRiwayat extends AppCompatActivity {
    protected Cursor cursor;
    database1 dbHelper;
    Button btnKembali;
    TextView txtNama, txtTglLahir, txtJenKel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_row);
        dbHelper = new database1(this);
        txtNama = findViewById(R.id.txtNama);
        txtTglLahir = findViewById(R.id.txtTglLahir);
        txtJenKel = findViewById(R.id.txtJenKel);
        btnKembali = findViewById(R.id.btnKembali);
        String no = getIntent().getStringExtra("id");
        String table = dbHelper.TABLENAME;
        String query = "SELECT*FROM "+table+" WHERE id = ?";
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery(query,  new String[] {no});
        cursor.moveToFirst();
        if (cursor.getCount()>0) {
            cursor.moveToPosition(0);
            txtNama.setText(cursor.getString(1));
            txtTglLahir.setText(cursor.getString(2));
            txtJenKel.setText(cursor.getString(3));
        }
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}