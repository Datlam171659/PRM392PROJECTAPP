package com.example.prm392project.view;

import com.example.prm392project.model.HealthMetricRequest;
import com.example.prm392project.model.HealthMetricResponse;
import com.example.prm392project.model.LoginRequest;
import com.example.prm392project.model.LoginResponse;
import com.example.prm392project.model.RegisterRequest;
import com.example.prm392project.model.RegisterResponse;
import com.example.prm392project.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserService {
    @Headers("Content-Type: application/json")
    @POST("api/user/login")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @Headers("Content-Type: application/json")
    @POST("api/user/register")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @GET("api/healthmetric/get-by-userId")
    Call<HealthMetricResponse> getHealthMetricsByUserId(@Query("userId") String userId);

    @Headers("Content-Type: application/json")
    @POST("api/healthmetric/add")
    Call<HealthMetricResponse> addHealthMetric(@Body HealthMetricRequest request);

    @GET("api/user/get-by-id/{id}")
    Call<UserResponse> getUserById(@Path("id") String id);
}
