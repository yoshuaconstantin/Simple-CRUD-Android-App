package com.joshua.r0th.crud2.ui.home;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.joshua.r0th.crud2.Repository;
import com.joshua.r0th.crud2.adapterdata;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private Context mContext;
    private MutableLiveData<List<adapterdata>> mData;
    private Repository mRepo;

    public void init(Context context){
        this.mContext = context;
        if (mData != null ){
            return;
        }
        mRepo = Repository.getInstance(mContext);
        mData = mRepo.getListData();
    }

    public LiveData<List<adapterdata>> getData() {
        return mData;
    }
}