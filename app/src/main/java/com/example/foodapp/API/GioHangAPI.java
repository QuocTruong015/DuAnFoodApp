package com.example.foodapp.API;

import com.example.foodapp.Model.GioHang;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GioHangAPI {
    @POST("api/giohang")
    Call<GioHang> addProductToCart(@Body GioHang gioHang);

    @GET("api/giohang")
    Call<List<GioHang>> getAllProductsInCart();

    @DELETE("api/giohang/{id}")
    Call<Void> removeProductFromCart(@Path("id") int id);

    @DELETE("api/giohang")
    Call<Void> removeProduct();
}
