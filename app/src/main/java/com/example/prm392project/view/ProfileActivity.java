package com.example.prm392project.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.prm392project.databinding.ProfileBinding;
import com.example.prm392project.presenter.ProfilePresenter;

public class ProfileActivity extends AppCompatActivity implements ProfileView {

    private ProfileBinding binding;
    private ProfilePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set up ViewBinding
        binding = ProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize Presenter
        presenter = new ProfilePresenter(this, this);

        // Load user profile data
        presenter.loadUserProfile();

        // Handle onClick for logout
        binding.tvDangXuat.setOnClickListener(v -> presenter.handleLogout());
    }

    @Override
    public void showUserInfo(String name) {
        // Display user name on the profile screen
        binding.profileName.setText(name);
    }

    @Override
    public void showError(String message) {
        // Handle error (e.g., show a toast or alert dialog)
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}

