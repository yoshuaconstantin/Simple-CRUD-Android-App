package com.joshua.r0th.crud2.ui.tools;

import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.adapterdata;
import com.joshua.r0th.crud2.database1;
import com.joshua.r0th.crud2.vieholderadapter;

import java.util.ArrayList;

public class ToolsFragment extends Fragment {


    TextView norumah, jentikdalam, jentikluar;

    private RecyclerView recyclerView;
    private ArrayList<adapterdata> items=new ArrayList<adapterdata>();
    private database1 database;
    public adapterdata adapter1;
    private vieholderadapter adapter2;
    private Cursor cursor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView =(ViewGroup) inflater.inflate(R.layout.fragment_riwayat, container, false);
        recyclerView=rootView.findViewById(R.id.recycleView);
        loadDatabase();
        return rootView;
    }
    public void loadDatabase(){
        database = new database1(getActivity());
        try {
            database.cekdatabasecopas();
            database.opendatabase();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            cursor = database.QueryData("select * from data_jentik");
            if (cursor != null) {
                if (cursor.moveToFirst()) {
                    do {
                        adapterdata item = new adapterdata();
                        item.setNomorRumah(cursor.getString(1));
                        item.setJentikDalam(cursor.getString(2));
                        item.setJentikLuar(cursor.getString(3));
                        items.add(item);
                    } while (cursor.moveToNext());

                }

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        adapter2= new vieholderadapter(getActivity(),items);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter2);
    }
}