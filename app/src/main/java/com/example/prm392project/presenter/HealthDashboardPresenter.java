package com.example.prm392project.presenter;

import com.example.prm392project.api.ApiService;
import com.example.prm392project.model.HealthMetric;
import com.example.prm392project.model.HealthMetricRequest;
import com.example.prm392project.model.HealthMetricResponse;
import com.example.prm392project.view.HealthDashboardView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HealthDashboardPresenter {
    private HealthDashboardView view;

    public HealthDashboardPresenter(HealthDashboardView view) {
        this.view = view;
    }

    public void fetchHealthMetrics(String userId) {
        ApiService.getUserService().getHealthMetricsByUserId(userId).enqueue(new Callback<HealthMetricResponse>() {
            @Override
            public void onResponse(Call<HealthMetricResponse> call, Response<HealthMetricResponse> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().getResults().isEmpty()) {
                    // Get the first HealthMetric from the results
                    HealthMetricResponse.HealthMetric healthMetric = response.body().getResults().get(0);
                    // Create an instance of HealthMetric to pass to the view
                    HealthMetric metricToPass = new HealthMetric();
                    metricToPass.setBloodSugar(healthMetric.getBloodSugar());
                    metricToPass.setUricAcid(healthMetric.getUricAcid());
                    metricToPass.setWeight(healthMetric.getWeight());
                    metricToPass.setBloodPressure(healthMetric.getBloodPressure());
                    // Pass it to the view
                    view.onHealthMetricsFetched(metricToPass);
                } else {
                    view.onError("No data available");
                }
            }

            @Override
            public void onFailure(Call<HealthMetricResponse> call, Throwable t) {
                view.onError(t.getMessage());
            }
        });
    }
    public void submitHealthMetrics(String metricId, String userId, int bloodSugar,
                                    int uricAcid, int weight, String bloodPressure) {
        HealthMetricRequest request = new HealthMetricRequest(
                metricId, userId, bloodSugar, uricAcid, weight, bloodPressure, ""
        );

        ApiService.getUserService().addHealthMetric(request).enqueue(new Callback<HealthMetricResponse>() {
            @Override
            public void onResponse(Call<HealthMetricResponse> call, Response<HealthMetricResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    view.onHealthMetricSubmitted();
                } else {
                    view.onError("Failed to submit health metrics");
                }
            }

            @Override
            public void onFailure(Call<HealthMetricResponse> call, Throwable t) {
                view.onError(t.getMessage());
            }
        });
    }
}
