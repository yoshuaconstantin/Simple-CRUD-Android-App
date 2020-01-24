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
    database1 myDb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_riwayat, container, false);
        myDb = new database1(getActivity());
        contohText = (TextView) rootView.findViewById(R.id.contohText);
        viewData();
        return rootView;
    }
    private void viewData() {
        contohText.setText(myDb.getData() != null ? myDb.getData() : "-");
    }
}