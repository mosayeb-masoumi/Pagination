package com.example.pagination;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Adapter  extends RecyclerView.Adapter<MyViewHolder> {

    List<Integer> MianList = new ArrayList<>();
    Context context;

    public Adapter(List<Integer> list, Context context) {
        this.MianList = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Integer model = MianList.get(position);
        holder.bindData(position);
    }

    @Override
    public int getItemCount() {
        return MianList.size();
    }


}
