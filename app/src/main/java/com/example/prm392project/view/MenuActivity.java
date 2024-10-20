package com.example.prm392project.view;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import java.util.List;

public class MenuActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DishAdapter dishAdapter;
    private ImageView imgAvatar;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lisdish); // Ensure this is the correct layout file
        imgAvatar = findViewById(R.id.imgAvatar);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // Grid of 2 columns
        imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to HealthDashboardActivity when the avatar is clicked
                Intent intent = new Intent(MenuActivity.this, HealthDashboardActivity.class); // Correct reference
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close this activity
            }
        });
        // Fetch data from API
        fetchDishes();
    }

    private void fetchDishes() {
        MenuService menuService = ApiClient.getMenuItems();
        Call<MenuItemResponse> call = menuService.getMenuItems(); // Fetch the dishes using the API

        call.enqueue(new Callback<MenuItemResponse>() {
            @Override
            public void onResponse(@NonNull Call<MenuItemResponse> call, @NonNull Response<MenuItemResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Lấy danh sách món ăn từ trường "results"
                    List<MenuItem> dishes = response.body().getResults();

                    // Pass 'this' as the context to the DishAdapter
                    dishAdapter = new DishAdapter(MenuActivity.this, dishes);
                    recyclerView.setAdapter(dishAdapter);
                    Log.d(TAG, "Success: " + response.body().toString());
                } else {
                    // Handle the case when response is not successful or body is null
                    Toast.makeText(MenuActivity.this, "Failed to fetch data from server.", Toast.LENGTH_SHORT).show();
                    Log.e("MenuActivity", "Response not successful or body is null");
                    Log.d("API_ERROR", "Lỗi API: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<MenuItemResponse> call, Throwable throwable) {
                // Handle failure
                Toast.makeText(MenuActivity.this, "Failed to connect to the server. Please try again later.", Toast.LENGTH_LONG).show();
                Log.e("MenuActivity", "onFailure: " + throwable.getMessage());
            }
        });
    }
}
