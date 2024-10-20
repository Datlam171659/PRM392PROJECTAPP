package com.example.prm392project.presenter;

import com.example.prm392project.api.ApiClient;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.DishDetailView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishDetailPresenter implements DishPresenterImp {
    private DishDetailView view;
    private MenuService apiService;

    public DishDetailPresenter(DishDetailView view) {
        this.view = view;
        this.apiService = ApiClient.getDishDetails(); // Initialize API service
    }

    @Override
    public void loadDishDetails(String dishId) {
        // Fetch dish details from API
        apiService.getDishDetails(dishId).enqueue(new Callback<MenuItem>() {
            @Override
            public void onResponse(Call<MenuItem> call, Response<MenuItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    view.showDishDetails(response.body());  // Pass data to view
                } else {
                    view.showError("Failed to load dish details");
                }
            }

            @Override
            public void onFailure(Call<MenuItem> call, Throwable t) {
                view.showError("API call failed: " + t.getMessage());
            }
        });
    }
}
