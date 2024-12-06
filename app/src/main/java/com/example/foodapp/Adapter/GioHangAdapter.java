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
import com.example.foodapp.Model.GioHang;
import com.example.foodapp.R;

import java.util.List;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.ViewHolder> {
    Context context;
    List<GioHang> gioHangList;
    private OnItemClickListener onItemClickListener;

    public GioHangAdapter(Context context, List<GioHang> gioHangList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.gioHangList = gioHangList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public GioHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new GioHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GioHangAdapter.ViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);

        // Gắn dữ liệu vào các view
        holder.txtTen.setText(gioHang.getTenSP());
        holder.txtGia.setText(String.format("₫%.2f", gioHang.getGia()));
        holder.txtSoLuong.setText(String.format("Số lượng: %d", gioHang.getSoLuong()));

        // Load ảnh từ URL (nếu `hinhAnh` là URL) hoặc từ resource (nếu là ID)
        // Sử dụng Glide hoặc Picasso nếu cần tải ảnh từ URL
        Glide.with(context).load(gioHang.getHinhAnh()).into(holder.imgFood);

        // Xử lý sự kiện click
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(gioHang);
            }
        });
    }


    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(GioHang gioHang);  // Phương thức được gọi khi nhấn vào mục
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imgFood;
        TextView txtTen, txtGia, txtSoLuong;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imageView4c);
            txtTen = itemView.findViewById(R.id.txtFoodnamec);
            txtGia = itemView.findViewById(R.id.txtPricec);
            txtSoLuong = itemView.findViewById(R.id.txtQuantitycc);
        }
    }
}
