package com.example.foodapp;

public class BurgerItem {
    String name,resName,price;
    int imgBurger, imgPlus;

    public BurgerItem(String name, String resName, String price, int imgBurger, int imgPlus) {
        this.name = name;
        this.resName = resName;
        this.price = price;
        this.imgBurger = imgBurger;
        this.imgPlus = imgPlus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getImgBurger() {
        return imgBurger;
    }

    public void setImgBurger(int imgBurger) {
        this.imgBurger = imgBurger;
    }

    public int getImgPlus() {
        return imgPlus;
    }

    public void setImgPlus(int imgPlus) {
        this.imgPlus = imgPlus;
    }
}
