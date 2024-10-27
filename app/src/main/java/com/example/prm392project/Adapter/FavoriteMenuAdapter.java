// FavoriteMenuAdapter.java
package com.example.prm392project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.model.DietItem;
import com.example.prm392project.model.MenuItem;

import java.util.List;

public class FavoriteMenuAdapter extends RecyclerView.Adapter<FavoriteMenuAdapter.MenuViewHolder> {
    private List<DietItem> favoriteMenuItems;

    public FavoriteMenuAdapter(List<DietItem> favoriteMenuItems) {
        this.favoriteMenuItems = favoriteMenuItems;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        DietItem dietItem = favoriteMenuItems.get(position);
        List<MenuItem> menuDietPlans = dietItem.getMenuDietPlans();

        // Convert the List of MenuItem to a single string to display
        StringBuilder menuNames = new StringBuilder();
        for (MenuItem item : menuDietPlans) {
            if (menuNames.length() > 0) {
                menuNames.append(", "); // Add a comma for separation
            }
            menuNames.append(item.getName()); // Assuming MenuItem has a getName() method
        }

        holder.menuName.setText(menuNames.toString());
    }


    @Override
    public int getItemCount() {
        return favoriteMenuItems.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView menuName;

        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            menuName = itemView.findViewById(R.id.favoriteMenuRecycler);
        }
    }
}
