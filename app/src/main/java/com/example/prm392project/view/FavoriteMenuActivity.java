package com.example.prm392project.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.Adapter.FavoriteMenuAdapter;
import com.example.prm392project.api.ApiClient;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.DietItem;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.presenter.FavoriteMenuPresenter;

import java.util.List;

public class FavoriteMenuActivity extends AppCompatActivity implements FavoriteMenuView {
    private FavoriteMenuPresenter presenter;
    private FavoriteMenuAdapter adapter;
    private Button createFavoriteMenuButton;
    private RecyclerView favoriteMenuRecycler;
    private String userId;
    private String dietplanId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorite_layout);
        dietplanId = getIntent().getStringExtra("DIET_ID");
        Log.e("FavoriteMenuActivity", "DietPlan ID: " + dietplanId);
        userId = getIntent().getStringExtra("USER_ID");
        if (userId == null || userId.isEmpty()) {
            showError("User ID is missing.");
            finish();
            return;
        }
        MenuService menuService = ApiClient.getMenuService();
        presenter = new FavoriteMenuPresenter(this, menuService);

        favoriteMenuRecycler = findViewById(R.id.favoriteMenuRecycler);
        createFavoriteMenuButton = findViewById(R.id.createFavoriteMenuButton);

        // Back button setup
        ImageView backIcon = findViewById(R.id.backIcon);
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteMenuActivity.this, ProfileActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("DIET_ID", dietplanId);  // Truyền lại dietplanId
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        favoriteMenuRecycler.setLayoutManager(new LinearLayoutManager(this));
        createFavoriteMenuButton.setOnClickListener(v -> showCreateMenuDialog());

        presenter.getFavoriteMenu(dietplanId);
    }

    private void showCreateMenuDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_create_menu, null);

        EditText dateAssignedInput = dialogView.findViewById(R.id.dateAssignedInput);
        EditText periodInput = dialogView.findViewById(R.id.periodInput); // Allows any text input

        builder.setView(dialogView)
                .setTitle("Create Favorite Menu")
                .setPositiveButton("Create", (dialog, which) -> {
                    String dateAssigned = dateAssignedInput.getText().toString();
                    String period = periodInput.getText().toString();

                    if (!dateAssigned.isEmpty() && !period.isEmpty()) {
                        DietItem newDietItem = new DietItem(userId, dateAssigned, period);
                        presenter.createFavoriteMenu(newDietItem);
                    } else {
                        showError("All fields are required.");
                    }
                })
                .setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss())
                .create()
                .show();
    }

    @Override
    public void showFavoriteMenu(List<MenuItem> dietItems) {
        adapter = new FavoriteMenuAdapter(this, dietItems, dietplanId, userId); // Pass dietplanId and userId here
        favoriteMenuRecycler.setAdapter(adapter);
        favoriteMenuRecycler.setVisibility(View.VISIBLE);
        createFavoriteMenuButton.setVisibility(View.GONE);
    }

    @Override
    public void showNoFavorites(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        createFavoriteMenuButton.setVisibility(View.VISIBLE);
        favoriteMenuRecycler.setVisibility(View.GONE);
    }

    @Override
    public void onMenuCreated() {
        showSuccess("Favorite menu created successfully");
        presenter.getFavoriteMenu(userId);
    }

    @Override
    public void storeDietPlanId(String dietPlanId) {
        this.dietplanId = dietPlanId;
    }
    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getUserId() {
        return userId;
    }

}
