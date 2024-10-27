package com.example.prm392project.api;

import com.example.prm392project.model.MenuItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MenuApi {

    // Example for getting a list of menu items
    @GET("dish/get-all")
    Call<List<MenuItem>> getMenuItems();

    // Example for getting a menu item by its ID
    @GET("dish/get-by-id/{id}")
    Call<MenuItem> getDishDetails(@Path("id") String dishId);

    @GET("dietplan/get-by-user-id/{userId}")
    Call<List<MenuItem>> getFavoriteMenu(@Path("userId") String userId);

    @POST("dietplan/add")
    Call<MenuItem> createFavoriteMenu(@Body MenuItem menuItem);
}
