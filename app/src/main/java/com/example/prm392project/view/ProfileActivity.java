package com.example.prm392project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392project.R;
import com.example.prm392project.databinding.ProfileBinding;
import com.example.prm392project.presenter.ProfilePresenter;

public class ProfileActivity extends AppCompatActivity implements ProfileView {
    private ProfileBinding binding;
    private ProfilePresenter presenter;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String userId = getIntent().getStringExtra("USER_ID");

        backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, MenuActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // Initialize Presenter with userId
        presenter = new ProfilePresenter(this, this, userId);
        presenter.loadUserProfile();

        binding.tvDangXuat.setOnClickListener(v -> presenter.handleLogout());
        binding.tvPremium.setOnClickListener(v -> {
            Intent intent = new Intent(ProfileActivity.this, UpdateAccountActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void showUserInfo(String name) {
        binding.profileName.setText(name);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}