package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.API.SanPhamAPI;
import com.example.foodapp.Adapter.SanPhamAdapter;
import com.example.foodapp.Model.SanPham;
import com.example.foodapp.Retrofit.ApiClient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BurgerPage extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SanPham> sanPhamList;
    ImageView imgBack;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_burger_page);

        // Set up edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        recyclerView = findViewById(R.id.recycleview);
        imgBack = findViewById(R.id.imageView);
        txtTitle = findViewById(R.id.textView6);

        // Retrieve data from Intent
        int danhMucId = getIntent().getIntExtra("DANH_MUC_ID", 1); // Default to 1
        String tenDanhMuc = getIntent().getStringExtra("DANH_MUC_TEN");

        // Set the title of the page
        txtTitle.setText(tenDanhMuc + " phổ biến");

        // Back button action
        imgBack.setOnClickListener(v -> finish());

        // Initialize product list
        sanPhamList = new ArrayList<>();

        // Fetch and display products by category ID
        getSanPhamByDanhMucId(danhMucId);
    }

    public void getSanPhamByDanhMucId(int danhMucId) {
        // Initialize API client
        SanPhamAPI sanPhamApi = ApiClient.getRetrofitInstance().create(SanPhamAPI.class);

        // Fetch products by category ID
        sanPhamApi.getSanPhamByDanhMuc(danhMucId).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SanPham> sanPhamListFromApi = response.body();

                    if (!sanPhamListFromApi.isEmpty()) {
                        sanPhamList.clear(); // Clear old data
                        sanPhamList.addAll(sanPhamListFromApi); // Add new products
                        ShowRecycleBurgerView(); // Display products
                    } else {
                        Toast.makeText(BurgerPage.this, "Không có sản phẩm trong danh mục này", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API_ERROR", "Lỗi: " + response.code());
                    Toast.makeText(BurgerPage.this, "Không tải được sản phẩm. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());
                Toast.makeText(BurgerPage.this, "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowRecycleBurgerView() {
        // Set up RecyclerView with a grid layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Set up adapter for RecyclerView
        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(this, sanPhamList, new SanPhamAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(SanPham sanPham) {
                // Navigate to DetailsItemPage with the selected product
                Intent intent = new Intent(BurgerPage.this, DetailsItemPage.class);
                intent.putExtra("sanpham", (Serializable) sanPham);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(sanPhamAdapter);
    }
}
