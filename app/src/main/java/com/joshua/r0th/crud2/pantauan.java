package com.joshua.r0th.crud2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;


public class pantauan extends AppCompatActivity {

    database1 myDb;

    EditText editNomorRumah,editJentikDalam,editJentikLuar;

    Button btnAddData;

    Button btnViewAll;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_pantauan);

        myDb = new database1(this);

        editNomorRumah = (EditText)findViewById(R.id.nomorrmh);

        editJentikDalam = (EditText)findViewById(R.id.jentikdirumah);

        editJentikLuar = (EditText)findViewById(R.id.jentikdiluarrumah);


        btnAddData = (Button)findViewById(R.id.tambahdata);

        btnViewAll = (Button)findViewById(R.id.lihatdata);



        AddData();

        viewAll();



    }



    //fungsi tambah

    public void AddData() {

        btnAddData.setOnClickListener(

                new View.OnClickListener() {

                    @Override

                    public void onClick(View v) {

                        boolean isInserted = myDb.insertData(editNomorRumah.getText().toString(),

                                editJentikDalam.getText().toString(),

                                editJentikLuar.getText().toString() );

                        if(isInserted == true)

                            Toast.makeText(pantauan.this,"Data Iserted",Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(pantauan.this,"Data Not Iserted",Toast.LENGTH_LONG).show();

                    }

                }

        );

    }



    //fungsi menampilkan data

    public void viewAll() {

        btnViewAll.setOnClickListener(

                new View.OnClickListener(){

                    @Override

                    public void onClick(View v) {

                        Cursor res = myDb.getAllData();

                        if(res.getCount() == 0) {

                            // show message

                            showMessage("Error","Noting Found");

                            return;

                        }



                        StringBuffer buffer = new StringBuffer();

                        while (res.moveToNext() ) {

                            buffer.append("NomorRumah :"+ res.getString(0)+"\n");

                            buffer.append("JentikDalam :"+ res.getString(1)+"\n");

                            buffer.append("JentikLuar :"+ res.getString(2)+"\n");



                        }



                        // show all data

                        showMessage("Data",buffer.toString());

                    }

                }

        );

    }



    //membuat alert dialog

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(Message);

        builder.show();

    }
    @Override
    public void onBackPressed() {
        Intent goLog = new Intent(pantauan.this, MainActivity.class);
        startActivity(goLog);
    }
}


