package com.example.foodapp.Model;

import java.lang.Double;

public class DonHang {
    private Integer id;
    private String tenDonHang;
    private Integer maSanPham;
    private String hinhAnh;
    private Integer soLuong;
    private Double gia;
    private String trangThai;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTenDonHang() {
        return tenDonHang;
    }

    public void setTenDonHang(String tenDonHang) {
        this.tenDonHang = tenDonHang;
    }

    public Integer getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(Integer maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public DonHang(String tenDonHang, Integer maSanPham, String hinhAnh, Integer soLuong, double gia, String trangThai) {
        this.tenDonHang = tenDonHang;
        this.maSanPham = maSanPham;
        this.hinhAnh = hinhAnh;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }
}
