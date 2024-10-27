package com.example.prm392project.presenter;

import android.util.Log;
import android.view.Menu;

import androidx.annotation.NonNull;

import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.DietItem;
import com.example.prm392project.model.FavoriteMenuResponse;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.model.MenuItemResponse;
import com.example.prm392project.view.FavoriteMenuView;
import java.io.IOException;
import java.util.ArrayList;
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

    public void getFavoriteMenu(String Id) {
        // Step 1: Fetch all menu items
        menuService.getMenuItems().enqueue(new Callback<MenuItemResponse>() {
            @Override
            public void onResponse(Call<MenuItemResponse> call, Response<MenuItemResponse> menuResponse) {
                if (menuResponse.isSuccessful() && menuResponse.body() != null) {
                    // Declare allMenus as final so it's accessible in the nested callback
                    final List<MenuItem> allMenus = menuResponse.body().getResults();
                    Log.d(TAG, "Fetched all menu items: " + Id);

                    // Step 2: Fetch favorite menu IDs
                    menuService.getFavoriteMenu(Id).enqueue(new Callback<FavoriteMenuResponse>() {
                        @Override
                        public void onResponse(@NonNull Call<FavoriteMenuResponse> call, @NonNull Response<FavoriteMenuResponse> favoriteResponse) {
                            if (favoriteResponse.isSuccessful() && favoriteResponse.body() != null) {
                                FavoriteMenuResponse.Result result = favoriteResponse.body().getResult();

                                if (result != null) {
                                    List<FavoriteMenuResponse.MenuDietPlan> favoritePlans = result.getMenuDietPlans();
                                    Log.d(TAG, "Fetched favorite menu IDs: " + favoritePlans);

                                    // Step 3: Filter matched menus by menuId
                                    List<MenuItem> matchedMenus = new ArrayList<>();
                                    for (FavoriteMenuResponse.MenuDietPlan dietPlan : favoritePlans) {
                                        for (MenuItem menuItem : allMenus) {
                                            if (menuItem.getId().equals(dietPlan.getMenuId())) {
                                                matchedMenus.add(menuItem);
                                                Log.d(TAG, "Matched Menu ID: " + menuItem.getId() + ", Name: " + menuItem.getMenuName());
                                            }
                                        }
                                    }

                                    // Step 4: Show the matched menus in the view
                                    if (matchedMenus.isEmpty()) {
                                        view.showNoFavorites("No matching menus found.");
                                    } else {
                                        view.showFavoriteMenu(matchedMenus);
                                    }
                                } else {
                                    view.showNoFavorites("No favorite menus available.");
                                }
                            } else {
                                view.showError("Failed to load favorite menus.");
                            }
                        }

                        @Override
                        public void onFailure(Call<FavoriteMenuResponse> call, Throwable t) {
                            logAndShowError("Failed to load favorite menus", t);
                        }
                    });
                } else {
                    view.showError("Failed to load menu items.");
                }
            }

            @Override
            public void onFailure(Call<MenuItemResponse> call, Throwable t) {
                logAndShowError("Failed to load menu items", t);
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
