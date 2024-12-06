package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.API.DonHangAPI;
import com.example.foodapp.Adapter.DonHangAdapter;
import com.example.foodapp.Model.DonHang;
import com.example.foodapp.Retrofit.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DonHangDangGiaoPage extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DonHangAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_don_hang_dang_giao_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewDonHangDangGiao);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDonHangByTrangThai("DangGiao");
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
                    adapter = new DonHangAdapter(DonHangDangGiaoPage.this, donHangList, new DonHangAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(DonHang donHang) {
                            Intent intent = new Intent(DonHangDangGiaoPage.this, TrangThaiDonHang.class);
                            startActivity(intent);
                        }
                    });
                    recyclerView.setAdapter(adapter);
                } else {
                    Toast.makeText(DonHangDangGiaoPage.this, "Không tìm thấy đơn hàng!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DonHang>> call, Throwable t) {
                Log.e("DonHangDangGiaoPage", "Error: " + t.getMessage());
                Toast.makeText(DonHangDangGiaoPage.this, "Lỗi khi gọi API!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}