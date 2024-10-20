package com.example.prm392project.view;

import com.example.prm392project.model.HealthMetric; // Make sure to import the correct class

public interface HealthDashboardView {
    void onHealthMetricsFetched(HealthMetric metric); // This should remain unchanged
    void onError(String message);
}