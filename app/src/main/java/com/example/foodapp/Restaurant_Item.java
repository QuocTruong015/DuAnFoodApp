package com.example.foodapp;

public class Restaurant_Item {
    int img;
    String resName, foodName;

    public Restaurant_Item(int img, String resName, String foodName) {
        this.img = img;
        this.resName = resName;
        this.foodName = foodName;
    }
    @Override
    public String toString() {
        return "Restaurant_Item{" +
                "img=" + img +
                ", resName='" + resName + '\'' +
                ", foodName='" + foodName + '\'' +
                '}';
    }
    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }
}
