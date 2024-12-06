package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context context;
    List<Category_Item> items;
    private OnItemClickListener onItemClickListener;

    // Constructor
    public MyAdapter(Context context, List<Category_Item> items) {
        this.context = context;
        this.items = items;
    }

    // Interface để xử lý sự kiện nhấn
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // Phương thức để truyền OnItemClickListener từ bên ngoài vào Adapter
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Tạo ViewHolder
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_category_view, parent, false), onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Gán dữ liệu cho hình ảnh trong ViewHolder
        holder.img.setImageResource(items.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }
}
