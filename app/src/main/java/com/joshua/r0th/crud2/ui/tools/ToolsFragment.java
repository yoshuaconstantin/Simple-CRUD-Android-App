package com.joshua.r0th.crud2.ui.tools;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.database1;

public class ToolsFragment extends Fragment {
    private TextView contohText;
    private TextView contohText2;
    private TextView contohText3;
    database1 myDb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);
        myDb = new database1(getActivity());
        contohText = (TextView) rootView.findViewById(R.id.contoh1);
        contohText2 = (TextView) rootView.findViewById(R.id.contoh2);
        contohText3 = (TextView) rootView.findViewById(R.id.contoh3);
        viewData();
        return rootView;
    }
    private void viewData() {
        contohText.setText(myDb.getData1() != null ? myDb.getData1() : "-");
        contohText2.setText(myDb.getData2() != null ? myDb.getData2() : "-");
        contohText3.setText(myDb.getData3() != null ? myDb.getData3() : "-");
    }
}