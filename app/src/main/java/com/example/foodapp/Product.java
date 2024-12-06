package com.example.foodapp;

import java.io.Serializable;

public class Product implements Serializable {
    private String name;
    private String price;
    private String quantity;
    private int imgResId;

    public Product(String name, String price, String quantity, int imgResId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgResId = imgResId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }
}
