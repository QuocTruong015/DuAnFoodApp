package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.foodapp.API.DonHangAPI;
import com.example.foodapp.API.ThongBaoAPI;
import com.example.foodapp.Model.DonHang;
import com.example.foodapp.Model.GioHang;
import com.example.foodapp.Model.ThongBao;
import com.example.foodapp.Retrofit.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Payment extends AppCompatActivity {

    List<GioHang> gioHangList;
    ImageView btnAddNewCard, imgBack;
    Button btnThanhToan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment);

        // Apply window insets for edge-to-edge experience
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize UI components
        btnAddNewCard = findViewById(R.id.btnAddVisa);
        imgBack = findViewById(R.id.imageViewBack);
        btnThanhToan = findViewById(R.id.btnThanhToan1);

        // Set listeners
        imgBack.setOnClickListener(v -> finish());

        btnAddNewCard.setOnClickListener(v -> {
            Intent intent = new Intent(Payment.this, NewCard.class);
            startActivity(intent);
        });

        // Retrieve and initialize gioHangList
        gioHangList = (List<GioHang>) getIntent().getSerializableExtra("gioHangList");
        if (gioHangList == null) {
            gioHangList = new ArrayList<>(); // Initialize an empty list if null
        }

        // Handle payment button click
        btnThanhToan.setOnClickListener(v -> {
            if (gioHangList != null && !gioHangList.isEmpty()) {
                // Iterate over gioHangList and process each item
                for (GioHang gioHang : gioHangList) {
                    // Create ThongBao object for each GioHang item
                    ThongBao thongBao = new ThongBao(gioHang.getHinhAnh(), "Đơn hàng của bạn đang được duyệt.", "Đặt hàng thành công!");
                    ThongBaoAPI thongBaoAPI = ApiClient.getRetrofitInstance().create(ThongBaoAPI.class);
                    Call<ThongBao> call = thongBaoAPI.addThongBao(thongBao);

                    // Handle the server response for ThongBao API
                    call.enqueue(new Callback<ThongBao>() {
                        @Override
                        public void onResponse(Call<ThongBao> call, Response<ThongBao> response) {
                            if (response.isSuccessful()) {
                                // Success: show a toast
                                Toast.makeText(getApplicationContext(), "Đơn hàng đã được gửi", Toast.LENGTH_SHORT).show();
                            } else {
                                // Error: handle server errors
                                Toast.makeText(getApplicationContext(), "Lỗi: " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ThongBao> call, Throwable t) {
                            // Error: handle connection failure
                            Toast.makeText(getApplicationContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                    // Create DonHang object for each GioHang item
                    DonHang donHang = new DonHang(
                            "Đơn hàng " + gioHang.getTenSP(),
                            gioHang.getSanPhamId(),
                            gioHang.getHinhAnh(),
                            gioHang.getSoLuong(),
                            gioHang.getGia(),
                            "ChoDuyet" // Trạng thái đơn hàng
                    );

                    DonHangAPI donHangAPI = ApiClient.getRetrofitInstance().create(DonHangAPI.class);
                    Call<DonHang> call1 = donHangAPI.addDonHang(donHang);

                    // Handle the server response for DonHang API
                    call1.enqueue(new Callback<DonHang>() {
                        @Override
                        public void onResponse(Call<DonHang> call1, Response<DonHang> response) {
                            if (response.isSuccessful()) {
                                // Success: show a toast
                                Toast.makeText(getApplicationContext(), "Đơn hàng đã được gửi", Toast.LENGTH_SHORT).show();
                            } else {
                                // Error: handle server errors
                                Toast.makeText(getApplicationContext(), "Lỗi: " + response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<DonHang> call1, Throwable t) {
                            // Error: handle connection failure
                            Toast.makeText(getApplicationContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                // Show a success message after processing all items in gioHangList
                Toast.makeText(Payment.this, "Đơn hàng của bạn thành công! Hãy xem thông báo để biết thêm.", Toast.LENGTH_SHORT).show();
            } else {
                // If gioHangList is empty or null
                Toast.makeText(Payment.this, "Giỏ hàng trống! Không có sản phẩm để thanh toán.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
