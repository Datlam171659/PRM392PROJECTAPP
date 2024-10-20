package com.example.prm392project.presenter;

import com.example.prm392project.api.ApiService;
import com.example.prm392project.model.RegisterRequest;
import com.example.prm392project.model.RegisterResponse;
import com.example.prm392project.view.RegisterView;
import com.example.prm392project.view.UserService;

import retrofit2.Call;
import retrofit2.Response;

public class RegisterPresenter {
    private RegisterView view;
    private UserService userService;


    public RegisterPresenter(RegisterView view) {
        this.view = view;
        this.userService = ApiService.getUserService();  // Kết nối tới API
    }

    public void register(RegisterRequest registerRequest) {
        view.showLoading();

        Call<RegisterResponse> registerResponseCall = userService.register(registerRequest);
        registerResponseCall.enqueue(new retrofit2.Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                view.hideLoading();
                if (response.isSuccessful()) {
                    view.onRegisterSuccess("Register success");
                } else {
                    view.onRegisterFailure("Register failed");
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                view.hideLoading();
                view.onRegisterFailure(t.getLocalizedMessage());
            }
        });
    }
}

