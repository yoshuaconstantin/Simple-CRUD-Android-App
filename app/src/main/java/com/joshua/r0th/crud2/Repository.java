package com.joshua.r0th.crud2;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class Repository {
    private static Context mContext;
    private static Repository instance;
    private List<adapterdata> data;
    private static database1 sqliteHelper;

    public static Repository getInstance(Context context){
        if(instance == null) {
            synchronized (SqliteHelper.class) {
                mContext = context;
                instance = new Repository();
                sqliteHelper = new database1(mContext);
            }
        }
        return instance;
    }

    public MutableLiveData<List<adapterdata>> getListData(){
        MutableLiveData<List<adapterdata>> data = new MutableLiveData<>();
        data.setValue(sqliteHelper.getAllData1());
        return data;
    }
}
