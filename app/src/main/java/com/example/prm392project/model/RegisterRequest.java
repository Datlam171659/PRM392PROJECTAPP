package com.example.prm392project.model;

public class RegisterRequest {
    private String username;
    private String fullName;
    private String email;
    private String password;
    private int userType;
    private int userRole;

    public RegisterRequest(String username, String fullName, String email, String password, int userType, int userRole) {
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.userType = userType;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public int getUserRole() {
        return userRole;
    }

    public void setUserRole(int userRole) {
        this.userRole = userRole;
    }
}

