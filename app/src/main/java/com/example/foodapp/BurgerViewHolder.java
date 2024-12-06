package com.example.foodapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BurgerViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView;
    TextView txtRestaurantName;
    TextView txtDescription;
    TextView txtPrice;
    ImageView btnAddToCart;

    public BurgerViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView = itemView.findViewById(R.id.imageView);
        txtRestaurantName = itemView.findViewById(R.id.txtRestaurantName);
        txtDescription = itemView.findViewById(R.id.txtDescription);
        txtPrice = itemView.findViewById(R.id.txtPrice);
        btnAddToCart = itemView.findViewById(R.id.btnAddToCart);


    }
}
