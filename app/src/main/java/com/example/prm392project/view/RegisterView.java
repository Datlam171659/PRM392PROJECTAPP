package com.example.prm392project.view;

public interface RegisterView {
    void showLoading();
    void hideLoading();
    void onRegisterSuccess(String message);
    void onRegisterFailure(String message);
}

