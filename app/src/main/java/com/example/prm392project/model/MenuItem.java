package com.example.prm392project.model;

public class MenuItem {
    private String dietitianId;
    private String menuName;
    private String imageUrl;
    private String description;
    private double totalCalories;
    private double carbohydrates;
    private double totalProtein;
    private double fat;
    private double fiber;
    private double sugar;
    private double purine;
    private double cholesterol;
    // Getters and Setters for each field
    public String getId() {
        return dietitianId;
    }

    public void setId(int id) {
        this.dietitianId = String.valueOf(id);
    }

    public String getName() {
        return menuName;
    }

    public void setName(String name) {
        this.menuName = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getCalories() { return totalCalories; }
    public void setCalories(int calories) { this.totalCalories = calories; }

    public double getCarbohydrates() { return carbohydrates; }
    public void setCarbohydrates(int carbohydrates) { this.carbohydrates = carbohydrates; }

    public double getProtein() { return totalProtein; }
    public void setProtein(double protein) { this.totalProtein = protein; }

    public double getFat() { return fat; }
    public void setFat(int fat) { this.fat = fat; }

    public double getFiber() { return fiber; }
    public void setFiber(double fiber) { this.fiber = fiber; }

    public double getSugar() { return sugar; }
    public void setSugar(int sugar) { this.sugar = sugar; }

    public double getPurine() { return purine; }
    public void setPurine(int purine) { this.purine = purine; }

    public double getCholesterol() { return cholesterol; }
    public void setCholesterol(int cholesterol) { this.cholesterol = cholesterol; }
}
