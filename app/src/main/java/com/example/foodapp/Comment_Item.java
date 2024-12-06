package com.example.foodapp;

public class Comment_Item {
    int img;
    String name, cmt;

    public Comment_Item(int img, String name, String cmt) {
        this.img = img;
        this.name = name;
        this.cmt = cmt;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }
}
