package com.example.foodapp.API;

import com.example.foodapp.Model.DanhMuc;
import com.example.foodapp.Model.NhaHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NhaHangAPI {
    @GET("api/nhahang/{id}")
    Call<NhaHang> getNhaHangById(@Path("id") int id);

    @GET("api/nhahang")
    Call<List<NhaHang>> getAllNhaHang();
}
