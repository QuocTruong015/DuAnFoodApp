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
import com.example.foodapp.Model.NhaHang;
import com.example.foodapp.R;

import java.util.List;

public class NhaHangAdapter extends RecyclerView.Adapter<NhaHangAdapter.ViewHolder> {

    private final Context context;
    private final List<NhaHang> nhaHangList;
    private final OnItemClickListener listener;

    // Constructor with listener
    public NhaHangAdapter(Context context, List<NhaHang> nhaHangList, OnItemClickListener listener) {
        this.context = context;
        this.nhaHangList = nhaHangList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_res_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NhaHang nhaHang = nhaHangList.get(position);

        Glide.with(holder.itemView.getContext())
                .load(nhaHang.getHinhAnh())
                .placeholder(R.drawable.res1) // Hình ảnh mặc định khi load
                .error(R.drawable.res1) // Hình ảnh khi xảy ra lỗi
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);

        holder.txtResName.setText(nhaHang.getTenNhaHang());
        holder.txtFoodName.setText(nhaHang.getTenMonAn());

        // Set onClickListener for the itemView to handle clicks
        holder.itemView.setOnClickListener(v -> listener.onItemClick(nhaHang));
    }

    @Override
    public int getItemCount() {
        return nhaHangList.size();
    }

    // ViewHolder chứa các view của item
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtResName, txtFoodName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgRes);
            txtResName = itemView.findViewById(R.id.txtResName);
            txtFoodName = itemView.findViewById(R.id.txtFoodName);
        }
    }

    // Interface để xử lý sự kiện click
    public interface OnItemClickListener {
        void onItemClick(NhaHang nhaHang);
    }
}
