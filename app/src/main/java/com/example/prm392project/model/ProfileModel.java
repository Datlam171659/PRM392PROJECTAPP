package com.example.prm392project.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.prm392project.api.ApiService;
import com.example.prm392project.view.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileModel {
    private Context context;
    private UserService userService;

    public ProfileModel(Context context) {
        this.context = context;
        userService = ApiService.getUserService();
    }

    public void getUserProfile(String userId, ProfileCallback callback) {
        userService.getUserById(userId).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().isSuccess()) {
                    callback.onSuccess(response.body().getResult());
                } else {
                    callback.onError("Failed to load user data");
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                callback.onError(t.getMessage());
            }
        });
    }

    public void clearSession() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public interface ProfileCallback {
        void onSuccess(UserResponse.Result user);
        void onError(String message);
    }
}