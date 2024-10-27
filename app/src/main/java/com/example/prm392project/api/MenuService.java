package com.example.prm392project.api;

import com.example.prm392project.model.DietItem;
import com.example.prm392project.model.FavoriteMenuResponse;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.model.MenuItemResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MenuService {
    // Example for getting a list of menu items
    @GET("menu/get-all")
    Call<MenuItemResponse> getMenuItems();
    @GET("menu/get-all-by-dietitian-id/{id}")
    Call<MenuItem> getDishDetails(@Path("id") String dishId);
    @GET("dietplan/get-by-id/{Id}")
    Call<FavoriteMenuResponse> getFavoriteMenu(@Path("Id") String userId);

    @POST("dietplan/add")
    Call<MenuItem> createFavoriteMenu(@Body DietItem dietItem);
    @POST("menudietplan/add")
    Call<MenuItem> Addmenudietplan(@Body MenuItem.DietPlanRequest dietPlanRequest);
    @PUT("menudietplan/delete")
    Call<Void> deleteMenuDietPlan(@Query("idDietPlan") String idDietPlan, @Query("idMenu") String idMenu);
}
