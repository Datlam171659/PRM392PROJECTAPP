package com.example.prm392project.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prm392project.databinding.AdminPageBinding;
import com.example.prm392project.presenter.AdminPresenter;
import com.example.prm392project.view.AdminView;

public class AdminActivity extends AppCompatActivity implements AdminView {

    private AdminPageBinding binding;
    private AdminPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = AdminPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Initialize presenter
        presenter = new AdminPresenter(this);

        setupListeners();
    }

    private void setupListeners() {
        binding.fab.setOnClickListener(view -> {
            // Add new action
            presenter.onAddNewClicked();
        });

        binding.btnAddUser.setOnClickListener(v -> {
            // Add user action
            presenter.onAddUserClicked();
        });

        binding.btnManageMenu.setOnClickListener(v -> {
            // Manage menu action
            presenter.onManageMenuClicked();
        });

        binding.btnReports.setOnClickListener(v -> {
            // Reports action
            presenter.onReportsClicked();
        });

        binding.btnSettings.setOnClickListener(v -> {
            // Settings action
            presenter.onSettingsClicked();
        });
    }

    @Override
    public void showSuccessMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }
}
