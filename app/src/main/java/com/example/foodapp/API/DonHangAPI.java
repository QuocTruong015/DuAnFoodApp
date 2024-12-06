package com.example.foodapp.API;

import com.example.foodapp.Model.DonHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface DonHangAPI {
    @GET("/api/donhang")
    Call<List<DonHang>> getAllDonHang();

    // Lấy đơn hàng theo trạng thái
    @GET("/api/donhang/trangthai")
    Call<List<DonHang>> getDonHangByTrangThai(@Query("trangThai") String trangThai);

    // Thêm mới đơn hàng
    @POST("/api/donhang")
    Call<DonHang> addDonHang(@Body DonHang donHang);
}
