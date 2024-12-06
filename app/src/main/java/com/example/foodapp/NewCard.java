package com.example.foodapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NewCard extends AppCompatActivity {

    EditText edtName, edtNumber, edtExpire, edtCVC;
    Button btnAdd;
    ImageView imgBack;
    Button btnThanhToan;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_new_card);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtName = findViewById(R.id.editTextName);
        edtNumber = findViewById(R.id.editTextNumber);
        edtExpire = findViewById(R.id.editTextExpire);
        edtCVC = findViewById(R.id.editTextCVC);
        btnAdd = findViewById(R.id.btnAddCard);
        imgBack = findViewById(R.id.imageViewBack);
        imgBack.setOnClickListener(v -> finish());


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAllFieldsFilled()) {
                    // Nếu tất cả các trường đã được điền
                    Intent intent = new Intent(NewCard.this, Payment.class);
                    startActivity(intent);
                } else {
                    // Hiển thị thông báo cho người dùng nếu còn trường chưa điền
                    Toast.makeText(NewCard.this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean isAllFieldsFilled() {
        return !edtName.getText().toString().trim().isEmpty() &&
                !edtNumber.getText().toString().trim().isEmpty() &&
                !edtExpire.getText().toString().trim().isEmpty() &&
                !edtCVC.getText().toString().trim().isEmpty();
    }
}