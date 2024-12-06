package com.example.foodapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import com.example.foodapp.Model.DanhMuc;
import com.example.foodapp.R;

import java.util.List;

public class DanhMucAdapter extends RecyclerView.Adapter<DanhMucAdapter.ViewHolder> {

    private Context context;
    private List<DanhMuc> danhMucList;
    private OnItemClickListener onItemClickListener;  // Interface để xử lý sự kiện click

    public DanhMucAdapter(Context context, List<DanhMuc> danhMucList, OnItemClickListener listener) {
        this.context = context;
        this.danhMucList = danhMucList;
        this.onItemClickListener = listener;  // Nhận listener
    }

    @NonNull
    @Override
    public DanhMucAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DanhMucAdapter.ViewHolder holder, int position) {
        DanhMuc danhMuc = danhMucList.get(position);

        // Glide để tải hình ảnh
        Glide.with(holder.itemView.getContext())
                .load(danhMuc.getHinhAnh())
                .diskCacheStrategy(DiskCacheStrategy.ALL) // Lưu cache để giảm tải
                .override(130, 70) // Kích thước tối ưu cho ImageView
                .into(holder.itemImg);

        // Thêm sự kiện click vào item
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(danhMuc);  // Gọi phương thức trong listener
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhMucList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(DanhMuc danhMuc);  // Phương thức được gọi khi nhấn vào mục
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImg = itemView.findViewById(R.id.imgViewCate);
        }
    }
}
