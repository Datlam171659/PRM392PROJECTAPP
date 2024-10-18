package com.example.prm392project.model;

public class LoginResponse {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private String id;
    private String token;
    private String message;

    public LoginResponse(String username, String password, String fullName, String email, String id, String token, String message) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.email = email;
        this.id = id;
        this.token = token;
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

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
