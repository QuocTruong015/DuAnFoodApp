package com.example.foodapp;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

import com.example.foodapp.Adapter.MessageAdapter;
import com.example.foodapp.Model.Message;

import java.util.ArrayList;
import java.util.List;

public class ChatPage extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtWelcome;
    EditText messEditText;
    ImageView sendButton;
    List<Message> messageList;
    MessageAdapter messageAdapter;
    int index;

    private List<String> botReplies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.recyclerViewChat);
        txtWelcome = findViewById(R.id.txtWelcome);
        messEditText = findViewById(R.id.mess_edt);
        sendButton = findViewById(R.id.btnSend);

        botReplies.add("Hi. Welcome to Food App, What do you want for today!");
        botReplies.add("Được, chúng tôi chuyên về những món ăn nhanh như pizza, burger cho bạn lựa chọn");
        botReplies.add("Hiện tại pizza đang được khuyến mãi về vận chuyển");
        botReplies.add("Pizza is always a good choice!");
        botReplies.add("I love burgers, they're awesome!");

        messageList = new ArrayList<>();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        messageAdapter = new MessageAdapter(messageList, this);
        recyclerView.setAdapter(messageAdapter);
        index = 0;

        sendButton.setOnClickListener(v -> {
            String ques = messEditText.getText().toString().trim();
            if (!ques.isEmpty()) {
                addToChat(ques, Message.SEND_BY_ME);
                messEditText.setText("");
                // Không sử dụng dịch thuật nữa
                addToChat(botReplies.get(index), Message.SEND_BY_BOT); // Giả sử bạn muốn gửi một phản hồi tĩnh.
                index++;
                txtWelcome.setVisibility(View.GONE);

            } else {
                Toast.makeText(this, "Please enter a message", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addToChat(String ques, String sendBy) {
        runOnUiThread(() -> {
            messageList.add(new Message(ques, sendBy));
            messageAdapter.notifyDataSetChanged();
            recyclerView.smoothScrollToPosition(messageAdapter.getItemCount());
        });
    }
}
