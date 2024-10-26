package com.example.prm392project;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392project.databinding.HealthpageBinding;
import com.example.prm392project.model.HealthMetric;
import com.example.prm392project.presenter.HealthDashboardPresenter;
import com.example.prm392project.view.HealthDashboardView;
import com.example.prm392project.view.MenuActivity;
import com.example.prm392project.view.ProfileActivity;

public class HealthDashboardActivity extends AppCompatActivity implements HealthDashboardView {

    private HealthDashboardPresenter presenter;
    private HealthpageBinding binding; // Declare the ViewBinding
    private Button btndish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = HealthpageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        btndish = findViewById(R.id.btndish); // Move this after setContentView

        setupViews();

        // Retrieve userId from Intent
        String userId = getIntent().getStringExtra("USER_ID");

        if (userId != null) {
            presenter = new HealthDashboardPresenter(this);
            presenter.fetchHealthMetrics(userId);
        } else {
            Toast.makeText(this, "User ID not found. Please log in again.", Toast.LENGTH_SHORT).show();
            finish();
        }

        btndish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthDashboardActivity.this, MenuActivity.class);
                intent.putExtra("USER_ID", userId);
                startActivity(intent);
                finish();
            }
        });

        binding.progressIndicator.setOnClickListener(v -> showHealthInputDialog());
    }


    private void setupViews() {
        // No need for findViewById, directly access views via binding
        binding.progressIndicator.setProgress(0);  // Start progress from 0
    }

    private void showHealthInputDialog() {
        // Create a Dialog instance
        Dialog dialog = new Dialog(this);

        // Inflate dialog_health_input.xml layout
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_health_input, null);
        dialog.setContentView(dialogView);

        // Set the width and height of the dialog
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        dialog.getWindow().setAttributes(params);

        // Get references to the input fields and submit button inside the dialog
        EditText etBloodSugar = dialogView.findViewById(R.id.etBloodSugar);
        EditText etUricAcid = dialogView.findViewById(R.id.etUricAcid);
        EditText etWeight = dialogView.findViewById(R.id.etWeight);
        EditText etBloodPressure = dialogView.findViewById(R.id.etBloodPressure);
        Button btnSubmit = dialogView.findViewById(R.id.btnSubmit);

        // Handle the submit button click inside the dialog
        btnSubmit.setOnClickListener(v -> {
            // Retrieve input values from the dialog fields
            String bloodSugar = etBloodSugar.getText().toString();
            String uricAcid = etUricAcid.getText().toString();
            String weight = etWeight.getText().toString();
            String bloodPressure = etBloodPressure.getText().toString();


            // Dismiss the dialog after submitting
            submitHealthMetrics(bloodSugar, uricAcid, weight, bloodPressure);
            dialog.dismiss();

            // Display a Toast with the values (for demonstration purposes)
            Toast.makeText(this, "Blood Sugar: " + bloodSugar +
                    "\nUric Acid: " + uricAcid +
                    "\nWeight: " + weight +
                    "\nBlood Pressure: " + bloodPressure, Toast.LENGTH_LONG).show();
        });

        // Show the dialog
        dialog.show();
    }

    @Override
    public void onHealthMetricsFetched(HealthMetric metric) {
        // Set values to TextViews via binding
        binding.tvBloodSugarValue.setText(String.valueOf(metric.getBloodSugar()));
        binding.tvUricAcidValue.setText(String.valueOf(metric.getUricAcid()));
        binding.tvWeightValue.setText(String.valueOf(metric.getWeight()));
        binding.tvBloodPressureValue.setText(metric.getBloodPressure());

        int progress = calculateHealthProgress(metric);
        animateProgress(progress);
    }

    private void submitHealthMetrics(String bloodSugar, String uricAcid, String weight, String bloodPressure) {
        try {
            int bloodSugarVal = Integer.parseInt(bloodSugar);
            int uricAcidVal = Integer.parseInt(uricAcid);
            int weightVal = Integer.parseInt(weight);

            String metricId = getIntent().getStringExtra("METRIC_ID");
            String userId = getIntent().getStringExtra("USER_ID");

            if (metricId != null && userId != null) {
                presenter.submitHealthMetrics(metricId, userId, bloodSugarVal, uricAcidVal, weightVal, bloodPressure);
            } else {
                onError("Missing metric ID or user ID");
            }
        } catch (NumberFormatException e) {
            onError("Please enter valid numbers");
        }
    }

    @Override
    public void onHealthMetricSubmitted() {
        Toast.makeText(this, "Health metrics submitted successfully", Toast.LENGTH_SHORT).show();
        // Refresh the metrics display
        String userId = getIntent().getStringExtra("USER_ID");
        if (userId != null) {
            presenter.fetchHealthMetrics(userId);
        }
    }

    @Override
    public void onError(String message) {
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    private int calculateHealthProgress(HealthMetric metric) {
        // Custom logic to calculate health progress based on the metrics
        return 75;
    }

    private void animateProgress(int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(binding.progressIndicator, "progress", 0, newProgress);
        animator.setDuration(1000);
        animator.start();
    }
}
