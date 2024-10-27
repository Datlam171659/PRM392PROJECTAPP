package com.example.prm392project.presenter;

import android.util.Log;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.DietItem;
import com.example.prm392project.model.FavoriteMenuResponse;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.FavoriteMenuView;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteMenuPresenter {
    private static final String TAG = "FavoriteMenuPresenter";
    private final FavoriteMenuView view;
    private final MenuService menuService;

    public FavoriteMenuPresenter(FavoriteMenuView view, MenuService menuService) {
        this.view = view;
        this.menuService = menuService;
    }

    public void getFavoriteMenu(String userId) {
        menuService.getFavoriteMenu(userId).enqueue(new Callback<FavoriteMenuResponse>() {
            @Override
            public void onResponse(Call<FavoriteMenuResponse> call, Response<FavoriteMenuResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    FavoriteMenuResponse favoriteResponse = response.body();

                    // Add a null check for result
                    if (favoriteResponse.getResult() == null) {
                        view.showError("No result found in response");
                        return;
                    }

                    FavoriteMenuResponse.Result result = favoriteResponse.getResult();

                    // Proceed with populating DietItem only if result is not null
                    DietItem dietItem = new DietItem(result.getUserId(), result.getDateAssigned(), result.getPeriod());
                    dietItem.setId(result.getId());
                    dietItem.setDeleted(result.isDeleted());
                    dietItem.setStatus(result.getStatus());
                    dietItem.setMenuDietPlans(result.getDietPlans());

                    if (dietItem.getMenuDietPlans() == null || dietItem.getMenuDietPlans().isEmpty()) {
                        view.showNoFavorites("Please register a favorite menu.");
                    } else {
                        view.showFavoriteMenu(Collections.singletonList(dietItem));
                    }
                } else {
                    handleApiError("Failed to load favorite menus", response);
                }
            }

            @Override
            public void onFailure(Call<FavoriteMenuResponse> call, Throwable throwable) {
                logAndShowError("Failed to load favorite menus", throwable);
            }
        });
    }



    public void createFavoriteMenu(DietItem dietItem) {
        // Ghi log các giá trị của dateAssign và period trước khi gọi API
        Log.d("FavoriteMenuPresenter", "Creating menu with dateAssign: " + dietItem.getDateAssigned() +
                ", period: " + dietItem.getPeriod());

        menuService.createFavoriteMenu(dietItem).enqueue(new Callback<DietItem>() {
            @Override
            public void onResponse(Call<DietItem> call, Response<DietItem> response) {
                if (response.isSuccessful() && response.body() != null) {
                    DietItem menuItem = response.body();

                    // Check if required fields are null
                    if (menuItem.getUserId() == null || menuItem.getId() == null) {
                        view.showError("Received incomplete data. Please try again.");
                        return;
                    }

                    // Handle the successful response as usual
                    view.onMenuCreated();
                    Log.d("FavoriteMenuPresenter", "Menu created successfully: " + response.body());
                } else {
                    handleApiError("Failed to create favorite menu", response);
                }
            }


            @Override
            public void onFailure(Call<DietItem> call, Throwable t) {
                view.showError("Error: " + t.getMessage());
                Log.e("FavoriteMenuPresenter", "API call failed: " + t.getMessage(), t);
            }
        });
    }
    private void handleApiError(String errorMessage, Response<?> response) {
        try {
            String errorBody = response.errorBody() != null ? response.errorBody().string() : "No error details";
            errorMessage += "\nError details: " + errorBody;
            Log.e(TAG, errorMessage);
            view.showError(errorMessage);
        } catch (IOException e) {
            Log.e(TAG, "Error reading error body", e);
            view.showError(errorMessage + "\nCould not retrieve detailed error.");
        }
    }

    private void logAndShowError(String message, Throwable t) {
        Log.e(TAG, message + ": " + t.getMessage(), t);
        view.showError("Error: " + t.getMessage());
    }
}
