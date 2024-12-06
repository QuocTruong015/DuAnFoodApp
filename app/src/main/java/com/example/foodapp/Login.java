package com.example.foodapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import android.content.SharedPreferences;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Login extends AppCompatActivity {

    EditText editTextEmail, editTextPass;
    CheckBox checkBoxRemember;
    TextView txtForgotPass, txtSignUp;
    ImageView imgFacebook, imgGmail, imgApple;
    Button btnLogin;
    FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        mAuth = FirebaseAuth.getInstance();

        AnhXa();

        sharedPreferences = getSharedPreferences("loginPrefs", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        checkRememberMe();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();
                String pass = editTextPass.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    Toast.makeText(Login.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (checkBoxRemember.isChecked()) {
                                        editor.putString("email", email);
                                        editor.putBoolean("rememberMe", true);
                                    } else {
                                        editor.clear(); // Xóa thông tin nếu không chọn
                                    }
                                    editor.apply();
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Login.this, "Login Successfully.",
                                            Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(Login.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        txtSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Register.class);
                startActivity(intent);
            }
        });

        txtForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "Please enter your registered email.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Gửi email đặt lại mật khẩu
                mAuth.sendPasswordResetEmail(email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login.this, "Reset email sent. Please check your inbox.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(Login.this, "Error sending reset email. Please check the email address.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

    }

    public void AnhXa() {
        editTextEmail = findViewById(R.id.edtEmailLogin);
        editTextPass = findViewById(R.id.edtPassLogin);
        checkBoxRemember = findViewById(R.id.ckBoxRemember);
        txtForgotPass = findViewById(R.id.txtForgotPass);
        txtSignUp = findViewById(R.id.txtSignUp);
        imgFacebook = findViewById(R.id.imgFb);
        imgGmail = findViewById(R.id.imgGmail);
        imgApple = findViewById(R.id.imgApple);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void checkRememberMe() {
        boolean isRemembered = sharedPreferences.getBoolean("rememberMe", false);
        if (isRemembered) {
            // Nếu đã lưu thông tin, tự động điền email
            editTextEmail.setText(sharedPreferences.getString("email", ""));
            checkBoxRemember.setChecked(true);
        }
    }
}
