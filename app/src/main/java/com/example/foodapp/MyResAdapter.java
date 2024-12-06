package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyResAdapter extends RecyclerView.Adapter<MyResViewHolder> {

    Context context;
    List<Restaurant_Item> lists;

    public MyResAdapter(Context context, List<Restaurant_Item> lists) {
        this.context = context;
        this.lists = lists;
    }

    @NonNull
    @Override
    public MyResViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyResViewHolder(LayoutInflater.from(context).inflate(R.layout.item_res_view,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyResViewHolder holder, int position) {
        holder.img.setImageResource(lists.get(position).getImg());
        holder.txtResName.setText(lists.get(position).getResName());
        holder.txtFoodName.setText(lists.get(position).getFoodName());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
