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

public class PizzaPage extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SanPham> sanPhamList;
    ImageView imgBack;
    TextView txtTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pizza_page);

        // Apply window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        recyclerView = findViewById(R.id.recycleviewMonNhaHang);
        txtTitle = findViewById(R.id.txtTenNhaHang);
        imgBack = findViewById(R.id.imageView);

        imgBack.setOnClickListener(v -> finish());

        // Retrieve data passed from previous activity
        int nhaHangId = getIntent().getIntExtra("NHA_HANG_ID", 1); // Default to 1 if no ID is provided
        String tenNhaHang = getIntent().getStringExtra("NHA_HANG_TEN");

        // Set title for the page
        txtTitle.setText("Nhà hàng " + tenNhaHang);

        // Initialize product list
        sanPhamList = new ArrayList<>();

        // Fetch and display products by restaurant ID
        getSanPhamByNhaHangId(nhaHangId);
    }

    public void ShowRecyclePizzaView() {
        if (sanPhamList == null) {
            sanPhamList = new ArrayList<>();
        }

        // Set up RecyclerView with a grid layout
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Create and set adapter for RecyclerView
        SanPhamAdapter sanPhamAdapter = new SanPhamAdapter(this, sanPhamList, sanPham -> {
            // Handle item click: open DetailsItemPage activity
            Intent intent = new Intent(PizzaPage.this, DetailsItemPage.class);
            intent.putExtra("sanpham", (Serializable) sanPham);
            startActivity(intent);
        });

        recyclerView.setAdapter(sanPhamAdapter);
    }

    public void getSanPhamByNhaHangId(int nhaHangId) {
        // Initialize API client
        SanPhamAPI sanPhamApi = ApiClient.getRetrofitInstance().create(SanPhamAPI.class);

        // Fetch products by restaurant ID
        sanPhamApi.getSanPhamByNhaHang(nhaHangId).enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<SanPham> sanPhamListFromApi = response.body();

                    if (!sanPhamListFromApi.isEmpty()) {
                        sanPhamList.clear();  // Clear existing list
                        sanPhamList.addAll(sanPhamListFromApi);  // Add fetched products
                        ShowRecyclePizzaView();  // Display products in RecyclerView
                    } else {
                        Toast.makeText(PizzaPage.this, "Không có sản phẩm trong danh mục này", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Log.e("API_ERROR", "Lỗi: " + response.code());
                    Toast.makeText(PizzaPage.this, "Không tải được sản phẩm. Vui lòng thử lại.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                Log.e("API_ERROR", "Lỗi kết nối: " + t.getMessage());
                Toast.makeText(PizzaPage.this, "Không thể kết nối đến server.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
