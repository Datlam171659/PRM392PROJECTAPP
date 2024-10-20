package com.example.prm392project.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prm392project.HealthDashboardActivity;
import com.example.prm392project.R;
import com.example.prm392project.databinding.LoginBinding;
import com.example.prm392project.model.LoginRequest;
import com.example.prm392project.model.LoginResponse;
import com.example.prm392project.presenter.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView changeRegister;
    private LoginBinding binding;
    private LoginPresenter presenter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginBinding.inflate(getLayoutInflater());
        setContentView(R.layout.login);

        presenter = new LoginPresenter(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        changeRegister = findViewById(R.id.changeRegister);

        changeRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(emailEditText.getText().toString()) || TextUtils.isEmpty(passwordEditText.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    LoginRequest loginRequest = new LoginRequest(email, password);
                    presenter.login(loginRequest);
                }
            }
        });
    }
    @Override
    public void showLoading() {
        // Hiển thị trạng thái đang tải
        Toast.makeText(this, "Loading...", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideLoading() {
        // Ẩn trạng thái đang tải
    }

    @Override
    public void onLoginSuccess(LoginResponse loginResponse) {
        // Xử lý khi đăng nhập thành công
        Toast.makeText(this, "Login Success", Toast.LENGTH_SHORT).show();
        Log.e("onLoginSuccess", "Login success" );
        // Chuyển sang màn hình chính hoặc lưu token
        String userId = String.valueOf(loginResponse.getResult().getId());
        Log.e("USER_ID", userId);
        Intent intent = new Intent(LoginActivity.this, HealthDashboardActivity.class);
        intent.putExtra("userId", userId);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailure(String message) {
        // Xử lý khi đăng nhập thất bại
        Toast.makeText(this, "Login Failed: " + message, Toast.LENGTH_SHORT).show();
        Log.e("onLoginFailure", "Login failed" );
        emailEditText.setText("");
        passwordEditText.setText("");
    }

}
