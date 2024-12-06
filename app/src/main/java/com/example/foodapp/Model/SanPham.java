package com.example.foodapp.Model;

import java.io.Serializable;

public class SanPham implements Serializable {
    private int id;

    private String tenSP;

    private String hinhAnh;

    private String dacDiem;

    private String chiTiet;

    private double gia;

    private DanhMuc danhMuc;

    private NhaHang nhaHang;

    public SanPham(int id, String tenSP, String hinhAnh, String dacDiem, String chiTiet, double gia, DanhMuc danhMuc, NhaHang nhaHang) {
        this.id = id;
        this.tenSP = tenSP;
        this.hinhAnh = hinhAnh;
        this.dacDiem = dacDiem;
        this.chiTiet = chiTiet;
        this.gia = gia;
        this.danhMuc = danhMuc;
        this.nhaHang = nhaHang;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getChiTiet() {
        return chiTiet;
    }

    public void setChiTiet(String chiTiet) {
        this.chiTiet = chiTiet;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public NhaHang getNhaHang() {
        return nhaHang;
    }

    public void setNhaHang(NhaHang nhaHang) {
        this.nhaHang = nhaHang;
    }
}
