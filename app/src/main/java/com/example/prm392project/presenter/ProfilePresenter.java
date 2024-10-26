package com.example.prm392project.presenter;

import android.content.Context;
import android.content.Intent;
import com.example.prm392project.model.ProfileModel;
import com.example.prm392project.model.UserResponse;
import com.example.prm392project.view.ProfileView;
import com.example.prm392project.view.WelcomeActivity;

public class ProfilePresenter {
    private ProfileView view;
    private ProfileModel model;
    private String userId;

    public ProfilePresenter(ProfileView view, Context context, String userId) {
        this.view = view;
        this.model = new ProfileModel(context);
        this.userId = userId;
    }

    public void loadUserProfile() {
        model.getUserProfile(userId, new ProfileModel.ProfileCallback() {
            @Override
            public void onSuccess(UserResponse.Result user) {
                if (user != null && user.getUsername() != null) {
                    view.showUserInfo(user.getUsername());
                } else {
                    view.showError("User data not found");
                }
            }

            @Override
            public void onError(String message) {
                view.showError(message);
            }
        });
    }

    public void handleLogout() {
        model.clearSession();
        Intent intent = new Intent((Context) view, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ((Context) view).startActivity(intent);
    }
}