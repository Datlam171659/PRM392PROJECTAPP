package com.example.prm392project.presenter;

import com.example.prm392project.view.ProfileView;

public class ProfilePresenter {
    private ProfileView view;

    public ProfilePresenter(ProfileView view) {
        this.view = view;
    }

    // Call this to load user data
    public void loadUserProfile() {
        // Fetch user data (normally this would be from a model or API)
        String userName = "Carely"; // Replace with real data

        if (userName != null) {
            view.showUserInfo(userName);
        } else {
            view.showError("Failed to load user data.");
        }
    }
}

