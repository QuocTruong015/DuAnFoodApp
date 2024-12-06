package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodapp.API.DanhMucAPI;
import com.example.foodapp.API.NhaHangAPI;
import com.example.foodapp.Adapter.DanhMucAdapter;
import com.example.foodapp.Adapter.NhaHangAdapter;
import com.example.foodapp.Model.DanhMuc;
import com.example.foodapp.Model.NhaHang;
import com.example.foodapp.Retrofit.ApiClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home extends AppCompatActivity {

    private RecyclerView recyclerView, recyclerResView;
    private BottomNavigationView bottomNavigationView;
    private DanhMucAdapter danhMucAdapter;
    private NhaHangAdapter nhaHangAdapter;
    private List<DanhMuc> danhMucList;
    private List<NhaHang> nhaHangList;
    private ImageView btnSearch, btnCart;
    private EditText edtSearch;

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.menu_home);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Handle edge-to-edge display
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        recyclerView = findViewById(R.id.recycleview);
        recyclerResView = findViewById(R.id.recycleviewRes);
        edtSearch = findViewById(R.id.edtSearch);
        btnSearch = findViewById(R.id.btnSearch1);
        btnCart = findViewById(R.id.imgCart);

        // Set up search and cart button actions
        setupSearchAndCart();

        // Set up bottom navigation
        setupBottomNavigation();

        // Display category and restaurant lists
        ShowRecycleCategoryView();
        ShowRecycleRestaurantView();
    }

    private void setupSearchAndCart() {
        edtSearch.setOnClickListener(v -> {
            Intent intent = new Intent(Home.this, HotDogPage.class); // Replace with appropriate search activity
            startActivity(intent);
        });

        btnCart.setOnClickListener(v -> {
            Intent intent = new Intent(this, Cart.class);
            startActivity(intent);
        });
    }

    private void setupBottomNavigation() {
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.menu_tichdiem){
                    startActivity(new Intent(Home.this, TichDiem.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_thongbao) {
                    startActivity(new Intent(Home.this, Noti.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_toi) {
                    startActivity(new Intent(Home.this, Info.class));
                    return true;
                } else if (item.getItemId() == R.id.menu_home){
                    return true;
                } else
                    return false;

            }
        });
    }

    public void ShowRecycleCategoryView() {
        // Initialize category list
        danhMucList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Set up adapter
        danhMucAdapter = new DanhMucAdapter(this, danhMucList, danhMuc -> {
            // Handle category click
            Intent intent = new Intent(Home.this, BurgerPage.class); // Replace with appropriate page
            intent.putExtra("DANH_MUC_ID", danhMuc.getId());
            intent.putExtra("DANH_MUC_TEN", danhMuc.getTenDanhMuc());
            Log.d("API_DATA", "Clicked Category ID: " + danhMuc.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(danhMucAdapter);

        // Fetch category list from API
        getDanhMucList();
    }

    private void getDanhMucList() {
        DanhMucAPI danhMucApi = ApiClient.getRetrofitInstance().create(DanhMucAPI.class);
        danhMucApi.getAllDanhMuc().enqueue(new Callback<List<DanhMuc>>() {
            @Override
            public void onResponse(Call<List<DanhMuc>> call, Response<List<DanhMuc>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    danhMucList.addAll(response.body());
                    danhMucAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Error Code: " + response.code());
                    Toast.makeText(Home.this, "Failed to load categories. Try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<DanhMuc>> call, Throwable t) {
                Log.e("API_ERROR", "Connection error: " + t.getMessage());
                Toast.makeText(Home.this, "Unable to connect to server.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void ShowRecycleRestaurantView() {
        // Initialize restaurant list
        nhaHangList = new ArrayList<>();
        recyclerResView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Set up adapter
        nhaHangAdapter = new NhaHangAdapter(this, nhaHangList, nhaHang -> {
            // Handle restaurant click
            Intent intent = new Intent(Home.this, PizzaPage.class); // Replace with appropriate page
            intent.putExtra("NHA_HANG_ID", nhaHang.getId());
            intent.putExtra("NHA_HANG_TEN", nhaHang.getTenNhaHang());
            Log.d("API_DATA", "Clicked Restaurant ID: " + nhaHang.getId());
            startActivity(intent);
        });
        recyclerResView.setAdapter(nhaHangAdapter);

        // Fetch restaurant list from API
        getNhaHangList();
    }

    private void getNhaHangList() {
        NhaHangAPI nhaHangApi = ApiClient.getRetrofitInstance().create(NhaHangAPI.class);
        nhaHangApi.getAllNhaHang().enqueue(new Callback<List<NhaHang>>() {
            @Override
            public void onResponse(Call<List<NhaHang>> call, Response<List<NhaHang>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    nhaHangList.addAll(response.body());
                    nhaHangAdapter.notifyDataSetChanged();
                } else {
                    Log.e("API_ERROR", "Error Code: " + response.code());
                    Toast.makeText(Home.this, "Failed to load restaurants. Try again later.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<NhaHang>> call, Throwable t) {
                Log.e("API_ERROR", "Connection error: " + t.getMessage());
                Toast.makeText(Home.this, "Unable to connect to server.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
