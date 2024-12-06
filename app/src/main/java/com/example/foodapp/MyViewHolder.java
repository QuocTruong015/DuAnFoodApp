package com.example.foodapp;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder extends RecyclerView.ViewHolder {

    ImageView img;

    public MyViewHolder(@NonNull View itemView, final MyAdapter.OnItemClickListener listener) {
        super(itemView);
        img = itemView.findViewById(R.id.imgViewCate);

        // Set sự kiện nhấn vào itemView
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra listener không null và vị trí hợp lệ
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position); // Gọi sự kiện nhấn
                    }
                }
            }
        });
    }
}
