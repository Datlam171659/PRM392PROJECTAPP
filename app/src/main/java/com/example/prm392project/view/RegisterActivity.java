package com.example.prm392project.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prm392project.MainActivity;
import com.example.prm392project.R;
import com.example.prm392project.api.ApiService;
import com.example.prm392project.databinding.SignupBinding;
import com.example.prm392project.model.RegisterRequest;
import com.example.prm392project.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText userNameEditText, nameEditText, emailEditText, passwordEditText;
    Button registerButton;

    private SignupBinding binding;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = SignupBinding.inflate(getLayoutInflater());
        setContentView(R.layout.signup);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.signup), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        userNameEditText = findViewById(R.id.userNameEditText);
        nameEditText = findViewById(R.id.nameEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(userNameEditText.getText().toString()) || TextUtils.isEmpty(nameEditText.getText().toString()) || TextUtils.isEmpty(emailEditText.getText().toString()) || TextUtils.isEmpty(passwordEditText.getText().toString())) {
                    Toast.makeText(RegisterActivity.this, "Required", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String userName = userNameEditText.getText().toString();
                    String name = nameEditText.getText().toString();
                    String email = emailEditText.getText().toString();
                    String password = passwordEditText.getText().toString();

                    RegisterRequest registerRequest = new RegisterRequest(userName, name, email, password);
                    register(registerRequest);
                }
            }
        });
    }

    public void register(RegisterRequest registerRequest) {

        Call<RegisterResponse> registerResponseCall = ApiService.getUserService().register(registerRequest);
        registerResponseCall.enqueue(new retrofit2.Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {

                    startActivity(Intent intent = new Intent(RegisterActivity.this, MainActivity.class));

                    Toast.makeText(RegisterActivity.this, "Register success", Toast.LENGTH_SHORT).show();
                    Log.e("Register", "onResponse: " + response.isSuccessful());

                } else {

                    Toast.makeText(RegisterActivity.this, "Register fail", Toast.LENGTH_SHORT).show();
                    Log.e("Register", "onResponse fail: " + response.isSuccessful());
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                // show error
                Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                Log.e("Register", "onFailure: " + t.getLocalizedMessage());

            }
        });
    }

}
