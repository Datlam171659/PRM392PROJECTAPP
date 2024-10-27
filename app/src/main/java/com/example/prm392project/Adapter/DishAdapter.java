package com.example.prm392project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.api.ApiClient;
import com.example.prm392project.api.MenuService;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.DetailDishActivity;
import com.google.gson.Gson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private final List<MenuItem> originalDishList;  // Store the original list
    private final String dietplanId;
    private List<MenuItem> filteredDishList;  // Store the filtered list
    private final Context context;
    private List<MenuItem> dishList;
    private final String userId; // Add this line

    public DishAdapter(Context context, List<MenuItem> dishList, String dietplanId, String userId) {
        this.context = context;
        this.dishList = dishList;
        this.originalDishList = dishList;
        this.filteredDishList = new ArrayList<>(dishList);
        this.dietplanId = dietplanId;
        this.userId = userId;  // Set userId from the parameter
    }
    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false);
        return new DishViewHolder(view);
    }

    public void updateList(List<MenuItem> newList) {
        dishList = newList;
        notifyDataSetChanged(); // Notify adapter to refresh the data
    }


    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        MenuItem dish = filteredDishList.get(position);
        holder.dishName.setText(dish.getName());

        // Load image using Glide
        Glide.with(context)
                .load(dish.getImageUrl())
                .placeholder(R.drawable.logowecare)
                .into(holder.dishImage);

        holder.nextIcon.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailDishActivity.class);
            intent.putExtra("USER_ID", userId);
            intent.putExtra("DIET_ID", dietplanId);
            intent.putExtra("dishName", dish.getName());
            intent.putExtra("dishImage", dish.getImageUrl());
            intent.putExtra("description", dish.getDescription());
            intent.putExtra("calories", dish.getCalories());
            intent.putExtra("protein", dish.getProtein());
            context.startActivity(intent);
        });
        holder.iconAdd.setOnClickListener(v -> {
            MenuService menuService = ApiClient.getMenuService();
            MenuItem.DietPlanRequest dietRequest = new MenuItem.DietPlanRequest(dish.getId(), dietplanId);

            // Convert the object to JSON and log it
            Gson gson = new Gson();
            String jsonBody = gson.toJson(dietRequest);
            Log.d("DishAdapter", "Request JSON: " + jsonBody);

            Call<MenuItem> call = menuService.Addmenudietplan(dietRequest);
            call.enqueue(new Callback<MenuItem>() {
                @Override
                public void onResponse(Call<MenuItem> call, Response<MenuItem> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, "Diet plan added successfully!", Toast.LENGTH_SHORT).show();
                    } else {
                        String errorMessage = "Failed to add diet plan. Error code: " + response.code();
                        if (response.errorBody() != null) {
                            try {
                                errorMessage += ", " + response.errorBody().string();
                            } catch (Exception e) {
                                errorMessage += ", Error parsing response";
                            }
                        }
                        Log.e("DishAdapter", errorMessage);
                        Toast.makeText(context, "Failed to add diet plan.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<MenuItem> call, Throwable t) {
                    Log.e("DishAdapter", "API call failed: " + t.getMessage(), t);
                    Toast.makeText(context, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });

    }

    @Override
    public int getItemCount() {
        return filteredDishList.size();  // Return filtered list size
    }

    // Function to filter the dish list based on the query
    public void filter(String query) {
        filteredDishList.clear();
        if (query.isEmpty()) {
            filteredDishList.addAll(originalDishList);  // Show all dishes when query is empty
        } else {
            for (MenuItem dish : originalDishList) {
                if (dish.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredDishList.add(dish);  // Add matching items
                }
            }
        }
        notifyDataSetChanged();  // Notify adapter of the changes
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        ImageView dishImage;
        ImageView nextIcon;
        ImageView iconAdd;
        public DishViewHolder(View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_name);
            dishImage = itemView.findViewById(R.id.dish_image);
            nextIcon = itemView.findViewById(R.id.arrowicon);
            iconAdd = itemView.findViewById(R.id.addicon);
        }
    }
}

