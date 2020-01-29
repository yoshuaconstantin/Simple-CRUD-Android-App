package com.joshua.r0th.crud2.ui.profile;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.SqliteHelper;
import com.joshua.r0th.crud2.database1;

import static android.content.Context.MODE_PRIVATE;

public class ProfileFragment extends Fragment {

    private TextView contohText;
    private TextView contohText1;
    private TextView contohText2;
    String emmail;
    SqliteHelper myDb;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        myDb = new SqliteHelper(getActivity());
        SharedPreferences prefs = this.getActivity().getSharedPreferences("crud", MODE_PRIVATE);
        emmail = prefs.getString("email", "No name defined");//"No name defined" is the default value.
        contohText = (TextView) rootView.findViewById(R.id.profile1);
        contohText1 = (TextView) rootView.findViewById(R.id.email);
        contohText2 = (TextView) rootView.findViewById(R.id.pswrdd);

        viewData();
        return rootView;
    }
    private void viewData() {
        contohText.setText(myDb.getData(emmail) != null ? myDb.getData(emmail) : "-");
        contohText1.setText(myDb.getData1(emmail) != null ? myDb.getData1(emmail) : "-");
        contohText2.setText(myDb.getData2(emmail) != null ? myDb.getData2(emmail) : "-");
    }


}