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

import com.bumptech.glide.Glide;
import com.example.prm392project.R;
import com.example.prm392project.model.MenuItem;
import com.example.prm392project.view.DetailDishActivity;

import java.util.List;

public class DishAdapter extends RecyclerView.Adapter<DishAdapter.DishViewHolder> {

    private final List<MenuItem> dishList;
    private final Context context;

    public DishAdapter(Context context, List<MenuItem> dishList) {
        this.context = context;
        this.dishList = dishList;
    }

    @NonNull
    @Override
    public DishViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your layout here
        View view = LayoutInflater.from(context).inflate(R.layout.item_menu, parent, false); // Ensure you are using the correct layout file
        return new DishViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DishViewHolder holder, int position) {
        MenuItem dish = dishList.get(position);
        holder.dishName.setText(dish.getName());

        // Load image using Glide
        Glide.with(context)
                .load(dish.getImageUrl())
                .placeholder(R.drawable.logowecare)
                .into(holder.dishImage);

        // Set a click listener for the arrow icon
        holder.nextIcon.setOnClickListener(v -> {
            // Launch DetailDishActivity with data passed via Intent
            Intent intent = new Intent(context, DetailDishActivity.class);
            intent.putExtra("dishName", dish.getName());
            intent.putExtra("description", dish.getDescription());
            intent.putExtra("calories", dish.getCalories());
            intent.putExtra("protein", dish.getProtein());
            context.startActivity(intent); // Start activity
        });
    }

    @Override
    public int getItemCount() {
        return dishList.size();
    }

    public static class DishViewHolder extends RecyclerView.ViewHolder {
        TextView dishName;
        ImageView dishImage;
        ImageView nextIcon; // Add the next icon reference

        public DishViewHolder(View itemView) {
            super(itemView);
            dishName = itemView.findViewById(R.id.dish_name);
            dishImage = itemView.findViewById(R.id.dish_image);
            nextIcon = itemView.findViewById(R.id.arrowicon); // Get reference to the arrow icon
        }
    }
}
