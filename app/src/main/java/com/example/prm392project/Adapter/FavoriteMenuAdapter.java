package com.example.prm392project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.api.ApiClient;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.DetailDishActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoriteMenuAdapter extends RecyclerView.Adapter<FavoriteMenuAdapter.MenuViewHolder> {

    private List<MenuItem> favoriteMenuItems;
    private final Context context;
    private final String dietplanId;
    private final String userId;
    public FavoriteMenuAdapter(Context context, List<MenuItem> favoriteMenuItems, String dietplanId, String userId) {
        this.context = context;
        this.favoriteMenuItems = favoriteMenuItems;
        this.dietplanId = dietplanId;
        this.userId = userId;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = favoriteMenuItems.get(position);

        if (menuItem != null) {
            holder.menuName.setText(menuItem.getMenuName() != null ? menuItem.getMenuName() : "No Name Available");

            // Use Glide to load the image
            Glide.with(context)
                    .load(menuItem.getImageUrl())
                    .placeholder(R.drawable.logowecare) // Add a placeholder image
                    .into(holder.menuImage);

            // Open dish details
            holder.nextIcon.setOnClickListener(v -> {
                Intent intent = new Intent(context, DetailDishActivity.class);
                intent.putExtra("USER_ID", userId);
                intent.putExtra("DIET_ID", dietplanId);
                intent.putExtra("dishName", menuItem.getMenuName());
                intent.putExtra("dishImage", menuItem.getImageUrl());
                intent.putExtra("description", menuItem.getDescription());
                intent.putExtra("calories", menuItem.getCalories());
                intent.putExtra("protein", menuItem.getProtein());
                context.startActivity(intent);
            });

            // Delete menu item
            holder.deleteIcon.setOnClickListener(v -> {
                deleteMenuItem(menuItem, position);
            });
        }
    }
    private void deleteMenuItem(MenuItem menuItem, int position) {
        MenuService menuService = ApiClient.getRetrofitInstance().create(MenuService.class);
        Call<Void> call = menuService.deleteMenuDietPlan(dietplanId, menuItem.getId());

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    favoriteMenuItems.remove(position);
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, favoriteMenuItems.size());
                } else {
                    Log.e("DELETE_MENU", "Failed to delete item: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("DELETE_MENU", "Error: " + t.getMessage());
            }
        });
    }
    @Override
    public int getItemCount() {
        return favoriteMenuItems.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView menuName;
        ImageView menuImage;
        ImageView nextIcon;
        ImageView deleteIcon;
        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.dish_name);
            menuImage = itemView.findViewById(R.id.dish_image);
            nextIcon = itemView.findViewById(R.id.arrowicon);
            deleteIcon = itemView.findViewById(R.id.deleteicon);
        }
    }
}
