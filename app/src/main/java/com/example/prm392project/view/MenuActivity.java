package com.example.prm392project.view;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.Adapter.DishAdapter;
import com.example.prm392project.HealthDashboardActivity;
import com.example.prm392project.R;
import com.example.prm392project.api.ApiClient;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.model.MenuItemResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DishAdapter dishAdapter;
    private ImageView imgAvatar;
    private List<MenuItem> allDishes; // Store all dishes for filtering
    private ImageView backIcon;
    private String dietplanId;
    private String userId;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisdish);

        imgAvatar = findViewById(R.id.imgAvatar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Grid of 2 columns
        backIcon = findViewById(R.id.backIcon);

        // Retrieve dietplanId and userId from intent and assign to class variables
        dietplanId = getIntent().getStringExtra("DIET_ID");
        userId = getIntent().getStringExtra("USER_ID");

        // Set click listeners
        backIcon.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, HealthDashboardActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish(); // Close this activity
        });

        imgAvatar.setOnClickListener(v -> {
            Intent intent = new Intent(MenuActivity.this, ProfileActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("DIET_ID", dietplanId);  // Pass dietplanId
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        fetchDishes();
    }

    private void fetchDishes() {
        MenuService menuService = ApiClient.getMenuService();
        Call<MenuItemResponse> call = menuService.getMenuItems();

        call.enqueue(new Callback<MenuItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<MenuItemResponse> call, @NonNull Response<MenuItemResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Store all dishes to filter later
                    Log.e("FavoriteMenuActivity", "user ID: " + userId);
                    allDishes = response.body().getResults();
                    dishAdapter = new DishAdapter(MenuActivity.this, allDishes, dietplanId, userId);
                    recyclerView.setAdapter(dishAdapter);
                } else {
                    Toast.makeText(MenuActivity.this, "Failed to fetch data from server.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<MenuItemResponse> call, Throwable t) {
                Toast.makeText(MenuActivity.this, "Failed to connect to the server. Please try again later.", Toast.LENGTH_LONG).show();
            }
        });
    }

    // Filter dishes based on the search query
    private void filterDishes(String query) {
        List<MenuItem> filteredList = new ArrayList<>();
        for (MenuItem item : allDishes) {
            if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        dishAdapter.updateList(filteredList);
    }
}
