package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.foodapp.Model.DanhMuc;
import com.example.foodapp.Model.SanPham;
import com.example.foodapp.OnItemClickListener;
import com.example.foodapp.R;

import java.util.List;

public class SanPhamAdapter extends RecyclerView.Adapter<SanPhamAdapter.ViewHolder> {
    Context context;
    List<SanPham> sanPhamList;
    private OnItemClickListener onItemClickListener;

    // Constructor
    public SanPhamAdapter(Context context, List<SanPham> sanPhamList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.sanPhamList = sanPhamList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public SanPhamAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new SanPhamAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SanPhamAdapter.ViewHolder holder, int position) {
        SanPham sanPham = sanPhamList.get(position);

        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(sanPham);  // Gọi phương thức trong listener
            }
        });

        // Load image using Glide
        Glide.with(holder.itemView.getContext())
                .load(sanPham.getHinhAnh())
                .placeholder(R.drawable.bur5)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);

        // Set the product details
        holder.txtFoodName.setText(sanPham.getTenSP());
        holder.txtDacDiem.setText(sanPham.getChiTiet());
        holder.txtGia.setText(String.valueOf(sanPham.getGia()));

        // Set the click listener for the item

    }

    public interface OnItemClickListener {
        void onItemClick(SanPham sanPham);  // Phương thức được gọi khi nhấn vào mục
    }

    @Override
    public int getItemCount() {
        return sanPhamList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtFoodName, txtDacDiem, txtGia;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imageView);
            txtFoodName = itemView.findViewById(R.id.txtRestaurantName);
            txtDacDiem = itemView.findViewById(R.id.txtDescription);
            txtGia = itemView.findViewById(R.id.txtPrice);
        }
    }
}
