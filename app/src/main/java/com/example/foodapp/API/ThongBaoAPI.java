package com.example.foodapp.API;

import com.example.foodapp.Model.ThongBao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ThongBaoAPI {
    @POST("api/thongbao")
    Call<ThongBao> addThongBao(@Body ThongBao thongBao);

    @GET("api/thongbao")
    Call<List<ThongBao>> getAllThongBao();
}
