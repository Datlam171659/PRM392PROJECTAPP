package com.example.prm392project.presenter;

import android.util.Log;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.DietItem;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.FavoriteMenuView;
import java.io.IOException;
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
        menuService.getFavoriteMenu(userId).enqueue(new Callback<List<DietItem>>() {
            @Override
            public void onResponse(Call<List<DietItem>> call, Response<List<DietItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<DietItem> favoriteMenuList = response.body();
                    view.showNoFavorites("Sucessfull");
                    if (favoriteMenuList.isEmpty()) {
                        view.showNoFavorites("Please register a favorite menu.");
                    } else {
                        view.showFavoriteMenu(favoriteMenuList);
                    }
                } else {
                    handleApiError("Failed to load favorite menus", response);
                }
            }

            @Override
            public void onFailure(Call<List<DietItem>> call, Throwable throwable) {
                logAndShowError("Failed to load favorite menus", throwable);
            }
        });
    }

    public void createFavoriteMenu(DietItem dietItem) {
        // Ghi log các giá trị của dateAssign và period trước khi gọi API
        Log.d("FavoriteMenuPresenter", "Creating menu with dateAssign: " + dietItem.getDateAssigned() +
                ", period: " + dietItem.getPeriod());

        menuService.createFavoriteMenu(dietItem).enqueue(new Callback<MenuItem>() {
            @Override
            public void onResponse(Call<MenuItem> call, Response<MenuItem> response) {
                if (response.isSuccessful()) {
                    view.onMenuCreated();
                    // Ghi log phản hồi thành công từ API
                    Log.d("FavoriteMenuPresenter", "Menu created successfully: " + response.body());
                } else {
                    String errorMessage = "Failed to create favorite menu: " + response.message();
                    Log.d("FavoriteMenuPresenter", "Creating menu with dateAssign: " + dietItem.getDateAssigned() +
                            ", period: " + dietItem.getPeriod() );
                    if (response.errorBody() != null) {
                        try {
                            String errorBody = response.errorBody().string();
                            errorMessage += "\nError details: " + errorBody;
                            Log.e("FavoriteMenuPresenter", errorMessage);
                        } catch (IOException e) {
                            Log.e("FavoriteMenuPresenter", "Error reading error body", e);
                        }
                    }
                    view.showError(errorMessage);
                }
            }

            @Override
            public void onFailure(Call<MenuItem> call, Throwable t) {
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
