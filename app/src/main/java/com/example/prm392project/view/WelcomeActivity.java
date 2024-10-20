package com.example.prm392project.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.prm392project.R;
import com.example.prm392project.databinding.WelcomeBinding;

public class WelcomeActivity extends AppCompatActivity {
    Button startButton;
    TextView alreadyHaveAccount;
    private WelcomeBinding binding;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
        EdgeToEdge.enable(this);

        binding = WelcomeBinding.inflate(getLayoutInflater());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.welcomeScreen), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        startButton = findViewById(R.id.startButton);
        alreadyHaveAccount = findViewById(R.id.alreadyHaveAccount);

        startButton.setOnClickListener(v -> {
            // Start the RegisterActivity
            Intent intent = new Intent(WelcomeActivity.this, RegisterActivity.class);
            startActivity(intent);
            finish();
        });

        alreadyHaveAccount.setOnClickListener(v -> {
            // Start the LoginActivity
            Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
