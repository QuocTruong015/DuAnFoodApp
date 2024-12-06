package com.example.foodapp.Model;

public class ThongBao {
    private int id;
    private String tenThongBao;
    private String moTa;
    private String hinhAnh;

    public ThongBao(String hinhAnh, String moTa, String tenThongBao) {
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.tenThongBao = tenThongBao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenThongBao() {
        return tenThongBao;
    }

    public void setTenThongBao(String tenThongBao) {
        this.tenThongBao = tenThongBao;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }
}
