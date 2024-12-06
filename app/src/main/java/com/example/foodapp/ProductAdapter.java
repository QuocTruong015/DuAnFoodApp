package com.example.foodapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private ArrayList<Product> cartList;
    Context context;

    public ProductAdapter(ArrayList<Product> cartList, Context context) {
        this.cartList = cartList;
        this.context = context;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = cartList.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice());
        holder.productQuantity.setText(product.getQuantity());
        holder.productImage.setImageResource(product.getImgResId());
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice, productQuantity;
        ImageView productImage;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

//            productName = itemView.findViewById(R.id.txtFoodname);
//            productPrice = itemView.findViewById(R.id.txtPrice);
//            productQuantity = itemView.findViewById(R.id.txtQuantitycc);
//            productImage = itemView.findViewById(R.id.imageView4c);
        }
    }
}

