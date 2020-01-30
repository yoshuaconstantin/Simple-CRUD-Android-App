package com.joshua.r0th.crud2;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class viewholder extends RecyclerView.ViewHolder {
    public TextView txtnorumah;
    public TextView txtjentikdalam;
    public TextView txtjentikluar;

    public viewholder(View itemView){
        super(itemView);
        txtnorumah = itemView.findViewById(R.id.txtNomrumah);
        txtjentikdalam = itemView.findViewById(R.id.txtJentikDalam);
        txtjentikluar = itemView.findViewById(R.id.JentikLuar);

    }
}
