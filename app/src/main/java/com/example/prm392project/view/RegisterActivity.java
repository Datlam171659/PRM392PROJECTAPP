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

import com.example.prm392project.R;
import com.example.prm392project.api.ApiService;
import com.example.prm392project.databinding.SigninBinding;
import com.example.prm392project.model.RegisterRequest;
import com.example.prm392project.model.RegisterResponse;
import com.example.prm392project.presenter.RegisterPresenter;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
    EditText userNameEditText, fullNameEditText, emailEditText, passwordEditText;
    Button registerButton;
    TextView changeLogin;

    private SigninBinding binding;
    private RegisterPresenter presenter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SigninBinding.inflate(getLayoutInflater());
        setContentView(R.layout.signin);

        presenter = new RegisterPresenter((RegisterView) this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signin), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        userNameEditText = findViewById(R.id.userNameEditText);
        fullNameEditText = findViewById(R.id.fullNameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);
        changeLogin = findViewById(R.id.changeLogin);

        changeLogin.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Kiểm tra dữ liệu người dùng nhập
                if (TextUtils.isEmpty(userNameEditText.getText().toString()) ||
                        TextUtils.isEmpty(fullNameEditText.getText().toString()) ||
                        TextUtils.isEmpty(emailEditText.getText().toString()) ||
                        TextUtils.isEmpty(passwordEditText.getText().toString())) {

                    Toast.makeText(RegisterActivity.this, "required", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Tạo đối tượng RegisterRequest
                String userName = userNameEditText.getText().toString();
                String name = fullNameEditText.getText().toString();
                String email = emailEditText.getText().toString();
                String password = passwordEditText.getText().toString();

                RegisterRequest registerRequest = new RegisterRequest(userName, name, email, password);

                // Gọi presenter để xử lý đăng ký
                presenter.register(registerRequest);
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
    public void onRegisterSuccess(String message) {
        // Xử lý khi đăng ký thành công
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("Register", "onResponse: " + message);
        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    public void onRegisterFailure(String message) {
        // Xử lý khi đăng ký thất bại
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        Log.e("Register", "onFailure: " + message);
    }

}
