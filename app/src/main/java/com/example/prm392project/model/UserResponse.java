package com.example.prm392project.model;
public class UserResponse {
    private Result result;
    private boolean isSuccess;
    private String message;

    // Getters and setters
    public Result getResult() { return result; }
    public void setResult(Result result) { this.result = result; }
    public boolean isSuccess() { return isSuccess; }
    public void setSuccess(boolean success) { isSuccess = success; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public static class Result {
        private String id;
        private String username;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
    }
}