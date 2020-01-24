package com.joshua.r0th.crud2.ui.send;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.joshua.r0th.crud2.LoginActivity;
import com.joshua.r0th.crud2.R;

public class SendFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

}