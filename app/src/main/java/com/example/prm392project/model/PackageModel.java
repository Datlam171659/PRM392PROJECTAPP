package com.example.prm392project.model;

public class PackageModel {
    private String packageName;
    private String price;

    public PackageModel(String packageName, String price) {
        this.packageName = packageName;
        this.price = price;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getPrice() {
        return price;
    }
}
