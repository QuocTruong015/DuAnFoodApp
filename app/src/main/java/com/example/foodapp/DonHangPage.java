package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.API.DonHangAPI;
import com.example.foodapp.Adapter.DonHangAdapter;
import com.example.foodapp.Model.DonHang;
import com.example.foodapp.Retrofit.ApiClient;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DonHangPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DonHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_don_hang_page);

        recyclerView = findViewById(R.id.recyclerViewDonHang);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDonHangByTrangThai("choDuyet");
    }

    private void fetchDonHangByTrangThai(String trangThai) {

        DonHangAPI donHangAPI = ApiClient.getRetrofitInstance().create(DonHangAPI.class);

        // Gọi API lấy danh sách đơn hàng
        donHangAPI.getDonHangByTrangThai(trangThai).enqueue(new Callback<List<DonHang>>() {
            @Override
            public void onResponse(Call<List<DonHang>> call, Response<List<DonHang>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DonHang> donHangList = response.body();
                    Log.e("DonHang", "Don Hang: " + response.body());
                    adapter = new DonHangAdapter(DonHangPage.this, donHangList, new DonHangAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(DonHang donHang) {

                        }
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(DonHangPage.this, "Không tìm thấy đơn hàng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.e("DonHangPage", "Error: " + t.getMessage());
                Toast.makeText(DonHangPage.this, "Lỗi khi gọi API!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
