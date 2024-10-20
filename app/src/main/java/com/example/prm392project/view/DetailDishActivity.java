package com.example.prm392project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.prm392project.HealthDashboardActivity;
import com.example.prm392project.R;
import com.example.prm392project.databinding.DishDetailBinding;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.presenter.DishDetailPresenter;
import com.example.prm392project.view.DishDetailView;

public class DetailDishActivity extends AppCompatActivity implements DishDetailView {

    private DishDetailPresenter presenter;
    private DishDetailBinding binding;
    private ImageView backIcon; // Declare back button ImageView

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize View Binding
        binding = DishDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Find the back button (ImageView) by its ID
        backIcon = findViewById(R.id.backIcon);

        // Set a click listener on the backIcon
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Go back to MenuListActivity when the backIcon is clicked
                Intent intent = new Intent(DetailDishActivity.this, MenuActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish(); // Close this activity
            }
        });

        // Get the data from intent
        Intent intent = getIntent();
        String dishName = intent.getStringExtra("dishName");
        String description = intent.getStringExtra("description");
        double calories = intent.getDoubleExtra("calories", 0.0); // default 0
        double protein = intent.getDoubleExtra("protein", 0.0); // default 0
        String dishImage = intent.getStringExtra("dishImage");

        // Set data to views
        binding.dishNameTextView.setText(dishName);
        binding.descriptionTextView.setText(description);
        binding.Caloriesdata.setText("Calories: " + calories);
        binding.proteindata.setText("Protein: " + protein + "g");

        // Load the dish image from the URL using Glide
        Glide.with(this)
                .load(dishImage)
                .placeholder(R.drawable.logowecare) // Replace with an appropriate placeholder image
                .into(binding.dishImage);
    }
    @Override
    public void showDishDetails(MenuItem dish) {
        // Set data using View Binding
        binding.dishNameTextView.setText(dish.getName());
        binding.descriptionTextView.setText(dish.getDescription());
        binding.Caloriesdata.setText("Calories: " + dish.getCalories());
        binding.proteindata.setText("Protein: " + dish.getProtein() + "g");
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
