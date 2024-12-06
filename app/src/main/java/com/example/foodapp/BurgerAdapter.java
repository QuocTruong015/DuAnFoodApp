package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BurgerAdapter extends RecyclerView.Adapter<BurgerViewHolder> {

    Context context;
    List<BurgerItem> items;
    private OnItemClickListener onItemClickListener;

    public BurgerAdapter(Context context, List<BurgerItem> items) {
        this.context = context;
        this.items = items;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Phương thức để truyền OnItemClickListener từ bên ngoài vào Adapter
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public BurgerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BurgerViewHolder(LayoutInflater.from(context).inflate(R.layout.grid_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull BurgerViewHolder holder, int position) {
        holder.txtRestaurantName.setText(items.get(position).getName());
        holder.txtDescription.setText(items.get(position).getResName());
        holder.txtPrice.setText(items.get(position).getPrice());
        holder.imageView.setImageResource(items.get(position).getImgBurger());
        holder.btnAddToCart.setImageResource(items.get(position).getImgPlus());

        holder.btnAddToCart.setOnClickListener(v -> {
            Toast.makeText(context,"Hello",Toast.LENGTH_SHORT).show();
        });

        holder.imageView.setOnClickListener(v ->{
            Intent intent = new Intent(context, DetailsItemPage.class);

            intent.putExtra("foodName",items.get(position).getName());
            intent.putExtra("resName",items.get(position).getResName());
            intent.putExtra("price",items.get(position).getPrice());
            intent.putExtra("img",items.get(position).getImgBurger());

            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
