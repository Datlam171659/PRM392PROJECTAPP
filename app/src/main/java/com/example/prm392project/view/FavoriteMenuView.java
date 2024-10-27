package com.example.prm392project.view;

import com.example.prm392project.model.DietItem;

import java.util.List;

public interface FavoriteMenuView {
    // Displays the list of favorite menu items
    void showFavoriteMenu(List<DietItem> menuItems);

    // Displays a message when there are no favorite menu items
    void showNoFavorites(String message);

    // Called when a new favorite menu is created successfully

    void onMenuCreated();


    // New method to store the dietPlanId
    void storeDietPlanId(String dietPlanId);
    // Displays an error message
    void showError(String message);

    // Displays a success message
    void showSuccess(String message);

    // Retrieves the user ID to pass it to the presenter
    String getUserId();
}
