package com.example.foodapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyResViewHolder extends RecyclerView.ViewHolder {

    ImageView img;
    TextView txtResName, txtFoodName;

    public MyResViewHolder(@NonNull View itemView) {
        super(itemView);

        img = itemView.findViewById(R.id.imgRes);
        txtResName = itemView.findViewById(R.id.txtResName);
        txtFoodName = itemView.findViewById(R.id.txtFoodName);
    }
}
