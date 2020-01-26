package com.joshua.r0th.crud2.ui.tools;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.database1;

import java.util.ArrayList;

public class ToolsFragment extends Fragment {


    TextView norumah, jentikdalam,jentikluar;


    private database1 SQLAdapter;

    database1 myDb;
    SimpleCursorAdapter cursorAdapter;
    Cursor cursor;
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);
        norumah = (TextView)rootView.findViewById(R.id.normh);
        jentikdalam = (TextView)rootView.findViewById(R.id.jentikdalam);
        jentikluar = (TextView)rootView.findViewById(R.id.jentikluar);
        ListView listView = (ListView)rootView.findViewById(R.id.listview1);
        myDb = new database1(getContext());

        ArrayList<String> thelist = new ArrayList<>();
        Cursor data = myDb.getlistall();

        if (data.getCount() == 0){
            Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_LONG).show();
        } else {
            while(data.moveToNext()){
                thelist.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1,thelist);
                listView.setAdapter(listAdapter);

        }

            return rootView;
    }

}