package com.example.prm392project.model;

public class DishModel {
    private String name;
    private String imageUrl;

    public DishModel(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;  // This method returns the dish name
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
