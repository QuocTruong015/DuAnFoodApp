package com.example.foodapp.API;

import com.example.foodapp.Model.NhaHang;
import com.example.foodapp.Model.SanPham;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SanPhamAPI {
    @GET("api/sanpham/{id}")
    Call<SanPham> getNhaHangById(@Path("id") int id);

    @GET("api/sanpham")
    Call<List<SanPham>> getAllNhaHang();

    // Lấy sản phẩm theo danh mục ID
    @GET("api/sanpham/danhmuc/{id}")
    Call<List<SanPham>> getSanPhamByDanhMuc(@Path("id") int danhMucId);

    // Lấy sản phẩm theo nhà hàng ID
    @GET("api/sanpham/nhahang/{id}")
    Call<List<SanPham>> getSanPhamByNhaHang(@Path("id") int nhaHangId);

    @GET("api/sanpham/search")
    Call<List<SanPham>> getSanPhamByName(@Query("tenSanPham") String tenSanPham);
}
