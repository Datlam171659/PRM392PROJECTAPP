package com.example.prm392project.view;

public interface LoginView {
    void showLoading();
    void hideLoading();
    void onLoginSuccess(String token);
    void onLoginFailure(String message);
}
