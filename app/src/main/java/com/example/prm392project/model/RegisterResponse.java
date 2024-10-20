package com.example.prm392project.model;

public class RegisterResponse {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String id;
    private boolean isSuccess;
    private String message;


    public RegisterResponse(String username, String password, String fullName, String email, String id, boolean isSuccess, String message) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.id = id;
        this.isSuccess = isSuccess;
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
