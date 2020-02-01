package com.joshua.r0th.crud2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.crud2.R;
import com.joshua.r0th.crud2.RecyclerViewAdapter;
import com.joshua.r0th.crud2.adapterdata;

import java.util.List;

public class HomeFragment extends Fragment {
    private HomeViewModel viewModel;
    private RecyclerViewAdapter adapter;
    private RecyclerView rvData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        rvData = view.findViewById(R.id.rvData);
        adapter = new RecyclerViewAdapter(getContext());

        viewModel.init(getContext());
        viewModel.getData().observe(getActivity(), new Observer<List<adapterdata>>() {
            @Override
            public void onChanged(List<adapterdata> adapterdata) {
                adapter.setItemData(adapterdata);
            }
        });

        rvData.setLayoutManager(new LinearLayoutManager(getContext()));
        rvData.setHasFixedSize(true);
        rvData.setAdapter(adapter);
    }
}