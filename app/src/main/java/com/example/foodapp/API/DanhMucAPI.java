package com.example.foodapp.API;

import com.example.foodapp.Model.DanhMuc;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface DanhMucAPI {
    @GET("api/danhmuc/{id}")
    Call<DanhMuc> getDanhMucById(@Path("id") int id);

    @GET("api/danhmuc")
    Call<List<DanhMuc>> getAllDanhMuc();

}


