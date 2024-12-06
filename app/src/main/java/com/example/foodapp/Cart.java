package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.API.GioHangAPI;
import com.example.foodapp.Adapter.GioHangAdapter;
import com.example.foodapp.Model.GioHang;
import com.example.foodapp.Retrofit.ApiClient;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart extends AppCompatActivity {

    List<GioHang> gioHangList;
    private RecyclerView recyclerViewCart; // Khai báo biến RecyclerView
    GioHangAdapter gioHangAdapter;
    ImageView imgBack;
    Button btnThanhToan;
    TextView txtTong;
    int Tong = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imgBack = findViewById(R.id.imageViewBack);
        btnThanhToan = findViewById(R.id.button);
        txtTong = findViewById(R.id.textView10);
        recyclerViewCart = findViewById(R.id.recyclerView); // Khởi tạo RecyclerView

        imgBack.setOnClickListener(v -> finish());

        btnThanhToan.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Payment.class);
            intent.putExtra("gioHangList", (Serializable) gioHangList); // Truyền danh sách
            startActivity(intent);
        });

        showRecycleviewGioHang();
        getGioHangTrongGioHang();
    }

    private void showRecycleviewGioHang() {
        gioHangList = new ArrayList<>();
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        gioHangAdapter = new GioHangAdapter(this, gioHangList, gioHang ->
                Toast.makeText(Cart.this, "Clicked on: " + gioHang.getTenSP(), Toast.LENGTH_SHORT).show());
        recyclerViewCart.setAdapter(gioHangAdapter);
    }

    private void getGioHangTrongGioHang() {
        GioHangAPI gioHangAPI = ApiClient.getRetrofitInstance().create(GioHangAPI.class); // Tạo instance của API

        gioHangAPI.getAllProductsInCart() // Gọi API để lấy danh sách sản phẩm trong giỏ
                .enqueue(new Callback<List<GioHang>>() {
                    @Override
                    public void onResponse(Call<List<GioHang>> call, Response<List<GioHang>> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            gioHangList.clear(); // Xóa danh sách cũ để tránh trùng lặp
                            gioHangList.addAll(response.body()); // Thêm danh sách mới từ API
                            for (GioHang gioHang : gioHangList) {
                                Tong += gioHang.getSoLuong() * gioHang.getGia();
                            }
                            txtTong.setText(" " + String.valueOf(Tong) + "đ");
                            gioHangAdapter.notifyDataSetChanged(); // Cập nhật RecyclerView
                        } else {
                            Toast.makeText(Cart.this, "Không thể tải dữ liệu giỏ hàng.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<List<GioHang>> call, Throwable t) {
                        Toast.makeText(Cart.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
