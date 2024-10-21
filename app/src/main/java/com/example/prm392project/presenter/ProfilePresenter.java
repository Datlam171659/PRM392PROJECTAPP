package com.example.prm392project.presenter;

import android.content.Context;
import android.content.Intent;
import com.example.prm392project.model.ProfileModel;
import com.example.prm392project.view.ProfileView;
import com.example.prm392project.view.WelcomeActivity;

public class ProfilePresenter {
    private ProfileView view;
    private ProfileModel model;

    public ProfilePresenter(ProfileView view, Context context) {
        this.view = view;
        this.model = new ProfileModel(context);  // Khởi tạo model
    }

    public void loadUserProfile() {
        // Giả lập việc load dữ liệu người dùng
        String userName = "Carely";

        if (userName != null) {
            view.showUserInfo(userName);
        } else {
            view.showError("Failed to load user data.");
        }
    }

    public void handleLogout() {
        // Gọi model để xóa dữ liệu session
        model.clearSession();

        // Điều hướng về WelcomeActivity
        Intent intent = new Intent((Context) view, WelcomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        ((Context) view).startActivity(intent);
    }
}
