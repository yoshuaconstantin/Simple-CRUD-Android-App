package com.joshua.r0th.crud2;

import android.app.Activity;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.joshua.r0th.crud2.ui.tools.ToolsFragment;

import java.util.Collections;
import java.util.List;

public class vieholderadapter extends RecyclerView.Adapter<viewholder> {
    private Activity activity;
    List<adapterdata> items= Collections.emptyList();

    public vieholderadapter(Activity activity, List<adapterdata> items){
        this.activity=activity;
        this.items=items;
    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_row,parent,false);

        return new viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
    holder.txtnorumah.setText(items.get(position).getNomorRumah());
    holder.txtjentikdalam.setText(items.get(position).getJentikDalam());
    holder.txtjentikluar.setText(items.get(position).getJentikLuar());

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
