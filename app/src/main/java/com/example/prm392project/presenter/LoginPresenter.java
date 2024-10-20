package com.example.prm392project.presenter;

import android.util.Log;

import com.example.prm392project.api.ApiService;
import com.example.prm392project.model.LoginRequest;
import com.example.prm392project.model.LoginResponse;
import com.example.prm392project.view.LoginView;
import com.example.prm392project.view.UserService;

import retrofit2.Call;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView view;
    private UserService userService;

    public LoginPresenter(LoginView view) {
        this.view = view;
        this.userService = ApiService.getUserService();  // Kết nối tới API
    }

    public void login(LoginRequest loginRequest) {
        view.showLoading();

        Call<LoginResponse> loginResponseCall = userService.login(loginRequest);
        loginResponseCall.enqueue(new retrofit2.Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideLoading();
                if (response.isSuccessful() && response.body().getToken() != null) {
                    LoginResponse loginResponse = response.body();

                    // Lấy token
                     String token = loginResponse.getToken();

                    // Lấy id từ đối tượng result
                    String id = loginResponse.getResult().getId();

                    // Hiển thị hoặc sử dụng id và token
                    Log.d("Login", "ID: " + id);

                    view.onLoginSuccess(token);
                } else {
                    view.onLoginFailure("Login failed");
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideLoading();
                view.onLoginFailure(t.getLocalizedMessage());
            }
        });
    }
}

