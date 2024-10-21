package com.example.prm392project.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static final String BASE_URL = "https://wecareexe201.azurewebsites.net/api/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static MenuService getMenuItems() {
        return getRetrofitInstance().create(MenuService.class);
    }
    public static MenuService getDishDetails() {
        return getRetrofitInstance().create(MenuService.class);
    }
}
