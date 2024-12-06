package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

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

public class HotDogPage extends AppCompatActivity {

    RecyclerView recyclerView;
    List<SanPham> sanPhamList;
    ImageView imgBack, btnSearch;
    EditText edtSearch;
    SanPhamAdapter sanPhamAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hot_dog_page);

        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        edtSearch = findViewById(R.id.edtSearch2);
        btnSearch = findViewById(R.id.btnSearch2);
        imgBack = findViewById(R.id.imageView);

        sanPhamList = new ArrayList<>();

        // Automatically request focus on EditText and show the soft keyboard
        edtSearch.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edtSearch, InputMethodManager.SHOW_IMPLICIT);

        // Set up RecyclerView and Adapter
        recyclerView = findViewById(R.id.recycleviewSearch1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Assuming 2 columns for grid
        sanPhamAdapter = new SanPhamAdapter(this, sanPhamList, sanPham -> {
            // Handle item click: Navigate to DetailsItemPage
            Intent intent = new Intent(HotDogPage.this, DetailsItemPage.class);
            intent.putExtra("sanpham", (Serializable) sanPham);
            startActivity(intent);
        });
        recyclerView.setAdapter(sanPhamAdapter);

        // Back button functionality
        imgBack.setOnClickListener(v -> finish());

        // Handle search button click
        btnSearch.setOnClickListener(v -> {
            String searchQuery = edtSearch.getText().toString().trim();
            if (!searchQuery.isEmpty()) {
                searchSanPhamByName(searchQuery);
            }
        });
    }

    // Call API to search products by name
    private void searchSanPhamByName(String tenSanPham) {
        // Initialize Retrofit API Service
        SanPhamAPI sanPhamAPI = ApiClient.getRetrofitInstance().create(SanPhamAPI.class);

        // Call API to get products by name
        Call<List<SanPham>> call = sanPhamAPI.getSanPhamByName(tenSanPham);

        call.enqueue(new Callback<List<SanPham>>() {
            @Override
            public void onResponse(Call<List<SanPham>> call, Response<List<SanPham>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Update product list
                    sanPhamList = response.body();

                    // Refresh adapter with new data
                    sanPhamAdapter = new SanPhamAdapter(HotDogPage.this, sanPhamList, sanPham -> {
                        Intent intent = new Intent(HotDogPage.this, DetailsItemPage.class);
                        intent.putExtra("sanpham", (Serializable) sanPham);
                        startActivity(intent);
                    });
                    recyclerView.setAdapter(sanPhamAdapter);
                    sanPhamAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<SanPham>> call, Throwable t) {
                // Handle API call failure (e.g., show an error message)
            }
        });
    }
}
