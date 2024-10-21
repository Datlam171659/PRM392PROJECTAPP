package com.example.prm392project.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.DetailDishActivity;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private final List<MenuItem> originalDishList;  // Store the original list
    private List<MenuItem> filteredDishList;  // Store the filtered list
    private final Context context;
    private List<MenuItem> dishList;
    public DishAdapter(Context context, List<MenuItem> dishList) {
        this.context = context;
        this.dishList = dishList;
        this.originalDishList = dishList;
        this.filteredDishList = new ArrayList<>(dishList); // Initially, all dishes are shown
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
            intent.putExtra("dishName", dish.getName());
            intent.putExtra("dishImage", dish.getImageUrl());
            intent.putExtra("description", dish.getDescription());
            intent.putExtra("calories", dish.getCalories());
            intent.putExtra("protein", dish.getProtein());
            context.startActivity(intent);
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

        public DishViewHolder(View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_name);
            dishImage = itemView.findViewById(R.id.dish_image);
            nextIcon = itemView.findViewById(R.id.arrowicon);
        }
    }
}

