package com.example.pagination;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView txt;

    public MyViewHolder(View view) {
        super(view);

        txt = itemView.findViewById(R.id.txt_row);
    }

    public void bindData(Integer position) {

        txt.setText(String.valueOf(position));

    }
}
