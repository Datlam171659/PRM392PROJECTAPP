package com.example.prm392project.presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.prm392project.view.ProfileView;
import com.example.prm392project.view.WelcomeActivity;

public class ProfilePresenter {
    private ProfileView view;
    private Context context;

    public ProfilePresenter(ProfileView view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void loadUserProfile() {
        // Simulated user data fetching
        String userName = "Carely"; // Replace with real data if needed

        if (userName != null) {
            view.showUserInfo(userName);
        } else {
            view.showError("Failed to load user data.");
        }
    }

    public void handleLogout() {
        // Clear stored tokens (if any)
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear(); // Clear all data
        editor.apply();

        // Redirect to WelcomeActivity
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Clear backstack
        context.startActivity(intent);
    }
}
