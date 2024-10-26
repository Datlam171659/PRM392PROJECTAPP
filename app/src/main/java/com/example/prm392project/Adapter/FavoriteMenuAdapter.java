// FavoriteMenuAdapter.java
package com.example.prm392project.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prm392project.R;
import com.example.prm392project.model.MenuItem;

import java.util.List;

public class FavoriteMenuAdapter extends RecyclerView.Adapter<FavoriteMenuAdapter.MenuViewHolder> {
    private List<MenuItem> favoriteMenuItems;

    public FavoriteMenuAdapter(List<MenuItem> favoriteMenuItems) {
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
        MenuItem menuItem = favoriteMenuItems.get(position);
        holder.menuName.setText(menuItem.getName());
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
