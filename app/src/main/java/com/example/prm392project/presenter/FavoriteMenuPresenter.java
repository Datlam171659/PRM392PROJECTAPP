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
    private FavoriteMenuView view;
    private MenuService menuService;

    public FavoriteMenuPresenter(FavoriteMenuView view, MenuService menuService) {
        this.view = view;
        this.menuService = menuService;
    }

    public void getFavoriteMenu(String userId) {
        menuService.getFavoriteMenu(userId).enqueue(new Callback<List<MenuItem>>() {
            @Override
            public void onResponse(Call<List<MenuItem>> call, Response<List<MenuItem>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MenuItem> menuItems = response.body();
                    if (menuItems.isEmpty()) {
                        view.showNoFavorites("Please register a favorite menu.");
                    } else {
                        view.showFavoriteMenu(menuItems);
                    }
                } else {
                    view.showError("Failed to load favorite menus");
                }
            }

            @Override
            public void onFailure(Call<List<MenuItem>> call, Throwable t) {
                view.showError("Error: " + t.getMessage());
            }
        });
    }

    public void createFavoriteMenu(DietItem dietItem) {
        menuService.createFavoriteMenu(dietItem).enqueue(new Callback<MenuItem>() {
            @Override
            public void onResponse(Call<MenuItem> call, Response<MenuItem> response) {
                if (response.isSuccessful()) {
                    view.onMenuCreated();
                    Log.d("FavoriteMenuPresenter", "Menu created successfully: " + response.body());
                } else {
                    String errorMessage = "Failed to create favorite menu: " + response.message();
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

    public void deleteFavoriteMenu(String menuId) {
        // Placeholder for delete functionality, if needed
    }
}
