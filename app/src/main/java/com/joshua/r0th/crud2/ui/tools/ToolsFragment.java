package com.joshua.r0th.crud2.ui.tools;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.database1;
import com.joshua.r0th.crud2.lihatRiwayat;

public class ToolsFragment extends Fragment {


    String[] daftar, listNama;
    ListView listView;
    database1 dbHelper;
    protected Cursor cursor;
    @SuppressLint("StaticFieldLeak")
    public static ToolsFragment ma;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);
        listView = rootView.findViewById(R.id.listview1);
        dbHelper = new database1(getContext());
        ma = this;
        refreshList();
        return rootView;
    }
    public void refreshList() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM data_jentik", null);
        daftar = new String[cursor.getCount()];
        listNama = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(0);
            listNama[cc] = cursor.getString(1);
        }
        listView.setAdapter(new ArrayAdapter<>(getContext(),
                android.R.layout.simple_list_item_1, listNama));
        listView.setSelected(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView adapterView, View view, int i, long l) {
                final String selection = daftar[i];
                final String showNama = listNama[i];
                final CharSequence[] dialogItem = {
                        "Lihat Biodata",
                        "Update Biodata",
                        "Hapus Biodata"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Pilihan");
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent read = new Intent(getContext(),
                                        lihatRiwayat.class);
                                read.putExtra("id", selection);
                                startActivity(read);
                                break;
                            case 1:

                                break;
                            case 2:

                                break;
                        }
                    }
                });
                builder.show();
            }
        });
        ((ArrayAdapter) listView.getAdapter()).notifyDataSetInvalidated();
    }
    }

