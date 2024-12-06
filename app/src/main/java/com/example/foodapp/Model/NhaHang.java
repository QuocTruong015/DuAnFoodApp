package com.example.foodapp.Model;

import java.io.Serializable;

public class NhaHang implements Serializable {
    private int id;

    private String hinhAnh;

    private String tenNhaHang;

    private String moTa;

    private String tenMonAn;

//    private List<SanPham> sanPhams;


    public NhaHang(int id, String hinhAnh, String tenNhaHang, String moTa, String tenMonAn) {
        this.id = id;
        this.hinhAnh = hinhAnh;
        this.tenNhaHang = tenNhaHang;
        this.moTa = moTa;
        this.tenMonAn = tenMonAn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getTenNhaHang() {
        return tenNhaHang;
    }

    public void setTenNhaHang(String tenNhaHang) {
        this.tenNhaHang = tenNhaHang;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getTenMonAn() {
        return tenMonAn;
    }

    public void setTenMonAn(String tenMonAn) {
        this.tenMonAn = tenMonAn;
    }
}
