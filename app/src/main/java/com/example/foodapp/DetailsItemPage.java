package com.example.foodapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodapp.API.DanhMucAPI;
import com.example.foodapp.API.GioHangAPI;
import com.example.foodapp.Model.GioHang;
import com.example.foodapp.Model.SanPham;
import com.example.foodapp.Retrofit.ApiClient;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailsItemPage extends AppCompatActivity {
    int quantity;
    RecyclerView recyclerView;
    List<Comment_Item> items;
    ArrayList<Product> cartList = new ArrayList<>(); // Danh sách giỏ hàng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_item_page);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Khai báo các view
        ImageView imgMinus = findViewById(R.id.imgMinus);
        TextView txtQuantity = findViewById(R.id.txtQuantity);
        ImageView imgPlus = findViewById(R.id.imgPlus);
        ImageView imgBack = findViewById(R.id.imageViewBack);
        ImageView btnCart = findViewById(R.id.imageView2);
        Button btnAddToCart = findViewById(R.id.buttonAddToCart);


        TextView txtFoodName = findViewById(R.id.txtFoodname);
        TextView txtPrice = findViewById(R.id.txtPrice);
        ImageView imageView = findViewById(R.id.imageView3);
        TextView detail = findViewById(R.id.textView8);

        // Nhận sản phẩm từ Intent
        Intent intent = getIntent();
        SanPham sanPham = (SanPham) intent.getSerializableExtra("sanpham");

        if (sanPham != null) {
            String ten = sanPham.getTenSP();
            String anh = sanPham.getHinhAnh();
            double gia = sanPham.getGia();

            // Hiển thị dữ liệu trên giao diện
            txtFoodName.setText(ten);
            txtPrice.setText(String.valueOf(gia));
            Glide.with(this)
                    .load(anh)
                    .placeholder(R.drawable.bur5)
                    .into(imageView);
        } else {
            Toast.makeText(this, "Không nhận được thông tin sản phẩm", Toast.LENGTH_SHORT).show();
            finish();
        }


        // Nút "Add to Cart"
        btnAddToCart.setOnClickListener(v -> {
            if (quantity < 1){
                Toast.makeText(this, "Số lượng ít nhất là 1", Toast.LENGTH_SHORT).show();
            } else {

                GioHang gioHang = new GioHang(sanPham.getId(), sanPham.getTenSP(), sanPham.getHinhAnh(), sanPham.getDacDiem(), quantity, sanPham.getGia());

                // Gửi yêu cầu thêm sản phẩm vào giỏ hàng
                GioHangAPI gioHangAPI = ApiClient.getRetrofitInstance().create(GioHangAPI.class);
                Call<GioHang> call = gioHangAPI.addProductToCart(gioHang);

                // Xử lý kết quả từ server
                call.enqueue(new Callback<GioHang>() {
                    @Override
                    public void onResponse(Call<GioHang> call, Response<GioHang> response) {
                        if (response.isSuccessful()) {
                            // Thông báo thành công
                            Toast.makeText(getApplicationContext(), "Đã thêm vào giỏ hàng", Toast.LENGTH_SHORT).show();
                        } else {
                            // Xử lý nếu có lỗi từ server (như lỗi 400, 500)
                            Toast.makeText(getApplicationContext(), "Lỗi: " + response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<GioHang> call, Throwable t) {
                        // Xử lý khi không thể kết nối tới server
                        Toast.makeText(getApplicationContext(), "Lỗi kết nối: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
            });

        // Nút mở giỏ hàng
        btnCart.setOnClickListener(v -> {
            Intent intent1 = new Intent(this, Cart.class);
            startActivity(intent1);
        });

        // Nút giảm số lượng
        imgMinus.setOnClickListener(v -> {
            quantity = Integer.parseInt(txtQuantity.getText().toString());
            if (quantity > 1) {
                quantity--;
                txtQuantity.setText(String.valueOf(quantity));
            }
        });

        // Nút tăng số lượng
        imgPlus.setOnClickListener(v -> {
            quantity = Integer.parseInt(txtQuantity.getText().toString());
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
        });

        // Nút quay lại
        imgBack.setOnClickListener(v -> finish());

        // Hiển thị danh sách bình luận
        showCmtRecyclerview();
    }

    // Hiển thị danh sách bình luận
    public void showCmtRecyclerview() {
        recyclerView = findViewById(R.id.recycleviewCmt);
        items = new ArrayList<>();
        items.add(new Comment_Item(R.drawable.ava1, "Lee Son Sin", "It's really good! I love this so much!!!"));

        items.add(new Comment_Item(R.drawable.ava2, "John Doe", "Amazing food and great service."));
        items.add(new Comment_Item(R.drawable.ava3, "Jane Smith", "Would definitely recommend this!"));


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        CommentRecycleviewAdapter commentRecycleviewAdapter = new CommentRecycleviewAdapter(items, this);
        recyclerView.setAdapter(commentRecycleviewAdapter);
    }
}
