package com.joshua.r0th.crud2.ui.gallery;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.database1;


public class GalleryFragment extends Fragment {
    Button btnAddData;
    Button btnViewAll;
    database1 myDb;

    EditText editNomorRumah,editJentikDalam,editJentikLuar;
    String contoh1;
    String contoh2;
    String contoh3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pantauan, container, false);
        btnAddData = rootView.findViewById(R.id.tambahdata);
        btnViewAll = rootView.findViewById(R.id.lihatdata);
        myDb = new database1(getActivity());
        editNomorRumah = (EditText)rootView.findViewById(R.id.nomorrmh);
        editJentikDalam = (EditText)rootView.findViewById(R.id.jentikdirumah);
        editJentikLuar = (EditText)rootView.findViewById(R.id.jentikdiluarrumah);
        contoh1 = editNomorRumah.getText().toString();
        contoh2 = editJentikDalam.getText().toString();
        contoh3 = editJentikLuar.getText().toString();

        AddData();
        viewAll();
        return rootView;
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

                            Toast.makeText(getContext(),"Data Iserted",Toast.LENGTH_LONG).show();

                        else

                            Toast.makeText(getContext(),"Data Not Iserted",Toast.LENGTH_LONG).show();

                    }

                }

        );

    }


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

                            buffer.append("NomorRumah :"+ res.getString(1)+"\n");

                            buffer.append("JentikDalam :"+ res.getString(2)+"\n");

                            buffer.append("JentikLuar :"+ res.getString(3)+"\n");



                        }



                        // show all data

                        showMessage("Data",buffer.toString());

                    }

                }

        );

    }
    //membuat alert dialog

    public void showMessage(String title, String Message){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setCancelable(true);

        builder.setTitle(title);

        builder.setMessage(Message);

        builder.show();

    }


}