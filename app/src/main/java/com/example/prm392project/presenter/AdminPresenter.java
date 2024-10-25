package com.example.prm392project.presenter;

import com.example.prm392project.view.AdminView;

public class AdminPresenter {

    private final AdminView view;

    public AdminPresenter(AdminView view) {
        this.view = view;
    }

    public void onAddNewClicked() {
        view.showSuccessMessage("Add New clicked!");
    }

    public void onAddUserClicked() {
        view.showSuccessMessage("Add User clicked!");
    }

    public void onManageMenuClicked() {
        view.showSuccessMessage("Manage Menu clicked!");
    }

    public void onReportsClicked() {
        view.showSuccessMessage("Reports clicked!");
    }

    public void onSettingsClicked() {
        view.showSuccessMessage("Settings clicked!");
    }
}
