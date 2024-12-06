package com.example.foodapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Info extends AppCompatActivity {

    ImageView btnChoXacNhan;
    ImageView btnDanhGia;
    ImageView btnChoGiaoHang;
    TextView txtYeuThich;
    TextView txtXemLichSu;
    ImageView btnChatBot;
    ImageView btnCall, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnChoXacNhan = findViewById(R.id.btnChoXacNhan);
        btnDanhGia = findViewById(R.id.btnDanhGia);
        btnChoGiaoHang = findViewById(R.id.btnChoGiaoHang);
        txtYeuThich = findViewById(R.id.txt2);
        txtXemLichSu = findViewById(R.id.txtXemLS);
        btnChatBot = findViewById(R.id.btnChat);
        btnCall = findViewById(R.id.btnCall);
        btnBack = findViewById(R.id.imageViewBackInfo);

        btnBack.setOnClickListener(v->finish());

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = "0777524910"; // Số điện thoại cần gọi
                if (!phoneNumber.isEmpty()) {
                    // Tạo Intent để mở màn hình gọi điện
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(intent); // Bắt đầu Intent
                } else {
                    // Hiển thị thông báo nếu số điện thoại trống
                    Toast.makeText(Info.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
                }
            }
        });


        btnChatBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, ChatPage.class);
                startActivity(intent);
            }
        });

        txtXemLichSu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, DonHangDaHoanThanh.class);
                startActivity(intent);
            }
        });

        txtYeuThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, SPYeuThich.class);
                startActivity(intent);
            }
        });

        btnChoXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, DonHangPage.class);
                startActivity(intent);
            }
        });

        btnDanhGia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, DonHangDaHoanThanh.class);
                startActivity(intent);
            }
        });

        btnChoGiaoHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Info.this, DonHangDangGiaoPage.class);
                startActivity(intent);
            }
        });


    }

}