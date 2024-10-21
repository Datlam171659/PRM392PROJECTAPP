package com.example.prm392project.model;

import android.content.Context;
import android.content.SharedPreferences;

public class ProfileModel {
    private Context context;

    public ProfileModel(Context context) {
        this.context = context;
    }

    public void clearSession() {
        // Xóa token hoặc dữ liệu đăng nhập trong SharedPreferences
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();  // Xóa tất cả dữ liệu
        editor.apply();
    }
}
