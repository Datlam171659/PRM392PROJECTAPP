package com.example.prm392project.model;

public class PackageModel {
    private String packageName;
    private String description; // Thêm thuộc tính mô tả

    public PackageModel(String packageName, String description) {
        this.packageName = packageName;
        this.description = description;
    }

    public String getPackageName() {
        return packageName;
    }

    public String getDescription() {
        return description; // Thêm getter cho mô tả
    }
}
