package com.example.foodapp;

import android.annotation.SuppressLint;
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

public class DonHangDaHoanThanh extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DonHangAdapter adapter;
    
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_don_hang_da_hoan_thanh);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView = findViewById(R.id.recyclerViewDonHangDaGiao);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        fetchDonHangByTrangThai("DaGiao");
        
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
                adapter = new DonHangAdapter(DonHangDaHoanThanh.this, donHangList, new DonHangAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(DonHang donHang) {
                        Intent intent = new Intent(DonHangDaHoanThanh.this, DanhGiaMonAn.class);
                        startActivity(intent);
                    }
                });
                recyclerView.setAdapter(adapter);
            } else {
                Toast.makeText(DonHangDaHoanThanh.this, "Không tìm thấy đơn hàng!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<List<DonHang>> call, Throwable t) {
            Log.e("DonHangDaHoanThanh", "Error: " + t.getMessage());
            Toast.makeText(DonHangDaHoanThanh.this, "Lỗi khi gọi API!", Toast.LENGTH_SHORT).show();
        }
    });
}
}