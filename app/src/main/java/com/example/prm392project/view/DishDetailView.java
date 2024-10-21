package com.example.prm392project.view;

import com.example.prm392project.model.MenuItem;

public interface DishDetailView {
    void showDishDetails(MenuItem dish);  // Show dish data

    void showError(String message);
}
