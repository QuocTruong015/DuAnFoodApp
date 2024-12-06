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
import com.example.foodapp.Model.DonHang;
import com.example.foodapp.R;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.ViewHolder> {
    private final Context context;
    private final List<DonHang> donHangList;
    private final OnItemClickListener listener;

    // Constructor with listener
    public DonHangAdapter(Context context, List<DonHang> donHangList, OnItemClickListener listener) {
        this.context = context;
        this.donHangList = donHangList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public DonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.donhang_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonHangAdapter.ViewHolder holder, int position) {
        DonHang donHang = donHangList.get(position);

        holder.txtTen.setText(donHang.getTenDonHang());
        holder.txtGia.setText(String.format("Giá: %s", donHang.getGia().toString()));
        holder.txtTrangThai.setText(String.format("Trạng thái: %s", donHang.getTrangThai()));

        // Sử dụng Glide để tải ảnh
        Glide.with(context)
                .load(donHang.getHinhAnh())
                .into(holder.img);

        // Thiết lập sự kiện click
        holder.itemView.setOnClickListener(v -> listener.onItemClick(donHang));
    }

    @Override
    public int getItemCount() {
        return donHangList != null ? donHangList.size() : 0;
    }

    // ViewHolder chứa các view của item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtTen, txtGia, txtTrangThai;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgDonHang);
            txtTen = itemView.findViewById(R.id.txtTenDonHang);
            txtGia = itemView.findViewById(R.id.txtTongDonHang);
            txtTrangThai = itemView.findViewById(R.id.txtTrangThaiDH);
        }
    }

    // Interface để xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(DonHang donHang);
    }
}
