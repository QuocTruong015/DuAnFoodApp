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
import com.example.foodapp.Model.ThongBao;
import com.example.foodapp.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ThongBaoAdapter extends RecyclerView.Adapter<ThongBaoAdapter.ViewHolder> {

    private Context context;
    private List<ThongBao> thongBaoList;

    // Constructor
    public ThongBaoAdapter(Context context, List<ThongBao> thongBaoList) {
        this.context = context;
        this.thongBaoList = thongBaoList;
    }

    @NonNull
    @Override
    public ThongBaoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate layout item thông báo
        View view = LayoutInflater.from(context).inflate(R.layout.thongbao_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThongBaoAdapter.ViewHolder holder, int position) {
        // Lấy đối tượng thông báo tại vị trí hiện tại
        ThongBao thongBao = thongBaoList.get(position);

        // Set dữ liệu cho các View
        holder.txtTen.setText(thongBao.getTenThongBao());
        holder.txtMoTa.setText(thongBao.getMoTa());
        Glide.with(holder.itemView.getContext())
                .load(thongBao.getHinhAnh())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {
        return thongBaoList.size(); // Số lượng thông báo trong danh sách
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView txtTen, txtMoTa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgSanPhamInThongBao);
            txtTen = itemView.findViewById(R.id.txtTrangThai);
            txtMoTa = itemView.findViewById(R.id.txtMoTaTB);
        }
    }
}
