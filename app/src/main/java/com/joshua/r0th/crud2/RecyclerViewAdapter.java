package com.joshua.r0th.crud2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public Context mContext;
    public List<adapterdata> mData;

    public RecyclerViewAdapter(Context context){
        this.mContext = context;

    }
    public void setItemData(List<adapterdata> data){
        this.mData = data;
    }
    
    public List<adapterdata> getData(){
        return mData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        adapterdata data = mData.get(position);

        holder.textViewName.setText(data.getNomorRumah());
        //holder.textViewName.setText(data.getNomorRumah());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewName, textViewSalary, textViewDepartment, textViewJoinDate;
        private Button buttonEditEmployee,buttonDeleteEmployee;

        ViewHolder(View itemView){
            super(itemView);

            textViewName = itemView.findViewById(R.id.textViewName);
            textViewDepartment = itemView.findViewById(R.id.textViewDepartment);
            textViewJoinDate = itemView.findViewById(R.id.textViewJoiningDate);
            textViewSalary = itemView.findViewById(R.id.textViewSalary);

            buttonEditEmployee = itemView.findViewById(R.id.buttonEditEmployee);
            buttonDeleteEmployee = itemView.findViewById(R.id.buttonDeleteEmployee);

        }
    }
}
