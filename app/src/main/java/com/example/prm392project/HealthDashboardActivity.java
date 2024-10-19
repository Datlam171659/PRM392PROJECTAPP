package com.example.prm392project;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.prm392project.model.HealthMetric;
import com.example.prm392project.presenter.HealthDashboardPresenter;
import com.example.prm392project.view.HealthDashboardView;

public class HealthDashboardActivity extends AppCompatActivity implements HealthDashboardView{

    private ProgressBar progressIndicator;
    private TextView bloodSugarTextView, uricAcidTextView, weightTextView, bloodPressureTextView;
    private HealthDashboardPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.healthpage);

        setupViews();
        presenter = new HealthDashboardPresenter(this); // Initialize the presenter
        presenter.fetchHealthMetrics("2d32bcd5-3203-4023-b0af-21fd1f443c88"); // Replace with dynamic userId if needed
    }

    private void setupViews() {
        progressIndicator = findViewById(R.id.progressIndicator);
        bloodSugarTextView = findViewById(R.id.tvBloodSugarValue);
        uricAcidTextView = findViewById(R.id.tvUricAcidValue);
        weightTextView = findViewById(R.id.tvWeightValue);
        bloodPressureTextView = findViewById(R.id.tvBloodPressureValue);

        progressIndicator.setProgress(0);  // Start progress from 0
    }

    @Override
    public void onHealthMetricsFetched(HealthMetric metric) {
        bloodSugarTextView.setText(String.valueOf(metric.getBloodSugar()));
        uricAcidTextView.setText(String.valueOf(metric.getUricAcid()));
        weightTextView.setText(String.valueOf(metric.getWeight()));
        bloodPressureTextView.setText(metric.getBloodPressure());

        int progress = calculateHealthProgress(metric);
        animateProgress(progress);
    }


    @Override
    public void onError(String message) {
        // You can show a toast or log the error
        Toast.makeText(this, "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    private int calculateHealthProgress(HealthMetric metric) {
        // Custom logic to calculate health progress based on the metrics
        return 75; // Placeholder for now
    }

    private void animateProgress(int newProgress) {
        ObjectAnimator animator = ObjectAnimator.ofInt(progressIndicator, "progress", 0, newProgress);
        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.start();
    }
}
