package com.example.prm392project.model;

import java.util.List;

public class FavoriteMenuResponse {
    private Result result;
    private boolean isSuccess;
    private String message;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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

    public static class Result {
        private String userId;
        private String dateAssigned;
        private String period;
        private int status;
        private User user;
        private List<MenuItem> dietPlans;
        private String id;
       private boolean isDeleted;

        // Getters and setters for each field
        public String getUserId() {
            return userId;
        }

        public String getId() {
            return id;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getDateAssigned() {
            return dateAssigned;
        }

        public void setDateAssigned(String dateAssigned) {
            this.dateAssigned = dateAssigned;
        }

        public String getPeriod() {
            return period;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<MenuItem> getDietPlans() {
            return dietPlans;
        }

        public void setMenuDietPlans(List<MenuItem> menuItems) {
            this.dietPlans = menuItems;
        }
    }
}
