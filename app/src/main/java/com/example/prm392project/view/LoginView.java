package com.example.prm392project.view;

import com.example.prm392project.model.LoginResponse;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void onLoginSuccess(LoginResponse loginResponse);
    void onLoginFailure(String message);
}
