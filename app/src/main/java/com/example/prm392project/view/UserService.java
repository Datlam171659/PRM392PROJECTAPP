package com.example.prm392project.view;

import com.example.prm392project.model.LoginRequest;
import com.example.prm392project.model.LoginResponse;
import com.example.prm392project.model.RegisterRequest;
import com.example.prm392project.model.RegisterResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserService {
    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("api/user/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);
}
