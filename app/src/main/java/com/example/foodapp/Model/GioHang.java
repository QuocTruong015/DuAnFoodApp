package com.example.foodapp.Model;

import java.io.Serializable;
import java.util.List;

public class GioHang implements Serializable {
    private int id;

    private int sanPhamId;

    private String tenSP;

    private String hinhAnh;

    private String dacDiem;

    private int soLuong;

    private double gia;

    private List<SanPham> sanPhams;

    public GioHang(int sanPhamId, String tenSP, String hinhAnh, String dacDiem, int soLuong, double gia) {
        this.sanPhamId = sanPhamId;
        this.tenSP = tenSP;
        this.hinhAnh = hinhAnh;
        this.dacDiem = dacDiem;
        this.soLuong = soLuong;
        this.gia = gia;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSanPhamId() {
        return sanPhamId;
    }

    public void setSanPhamId(int sanPhamId) {
        this.sanPhamId = sanPhamId;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getDacDiem() {
        return dacDiem;
    }

    public void setDacDiem(String dacDiem) {
        this.dacDiem = dacDiem;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public List<SanPham> getSanPhams() {
        return sanPhams;
    }

    public void setSanPhams(List<SanPham> sanPhams) {
        this.sanPhams = sanPhams;
    }
}
