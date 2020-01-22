package com.joshua.r0th.crud2.ui.gallery;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.database1;

public class GalleryFragment extends AppCompatActivity {

    database1 myDb;

    EditText editNomorRumah,editJentikDalam,editJentikLuar,editTextId;

    Button btnAddData;

    Button btnViewAll;





    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

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

                            Toast.makeText(GalleryFragment.this,"Data Iserted",Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(GalleryFragment.this,"Data Not Iserted",Toast.LENGTH_LONG).show();

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

}


