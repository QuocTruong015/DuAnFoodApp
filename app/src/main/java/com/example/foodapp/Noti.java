package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.API.ThongBaoAPI;
import com.example.foodapp.Adapter.ThongBaoAdapter;
import com.example.foodapp.Model.ThongBao;
import com.example.foodapp.Retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Noti extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ThongBaoAdapter thongBaoAdapter;
    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_noti);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewThongBao);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        btnBack = findViewById(R.id.imageViewBack);
        btnBack.setOnClickListener(v->finish());

        // Gọi API lấy danh sách thông báo
        loadThongBao();
    }
    private void loadThongBao() {
        ThongBaoAPI api = ApiClient.getRetrofitInstance().create(ThongBaoAPI.class);
        Call<List<ThongBao>> call = api.getAllThongBao();

        call.enqueue(new Callback<List<ThongBao>>() {
            @Override
            public void onResponse(Call<List<ThongBao>> call, Response<List<ThongBao>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Gán dữ liệu cho adapter
                    thongBaoAdapter = new ThongBaoAdapter(Noti.this, response.body());
                    recyclerView.setAdapter(thongBaoAdapter);
                } else {
                    Log.e("ThongBaoActivity", "Không có dữ liệu hoặc lỗi server");
                }
            }

            @Override
            public void onFailure(Call<List<ThongBao>> call, Throwable t) {
                Log.e("ThongBaoActivity", "Lỗi API: " + t.getMessage());
            }
        });
    }
}