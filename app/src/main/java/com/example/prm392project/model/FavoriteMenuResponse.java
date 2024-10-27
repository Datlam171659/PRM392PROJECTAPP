package com.example.prm392project.model;

import com.google.gson.Gson;

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
        private List<MenuDietPlan> menuDietPlans;
        private String id;
        private String createdBy;
        private String createdDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;
        private boolean isDeleted;

        // Getters and setters for each field
        public String getUserId() {
            return userId;
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

        public List<MenuDietPlan> getMenuDietPlans() {
            return menuDietPlans;
        }

        public void setMenuDietPlans(List<MenuDietPlan> menuDietPlans) {
            this.menuDietPlans = menuDietPlans;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }

    public static class MenuDietPlan {
        private String menuId;
        private String dietPlanId;
        private String menu;
        private String dietPlan;
        private String id;
        private String createdBy;
        private String createdDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;
        private boolean isDeleted;

        // Getters and setters for each field
        public String getMenuId() {
            return menuId;
        }
        public void setMenuId(String menuId) {
            this.menuId = menuId;
        }

        public String getDietPlanId() {
            return dietPlanId;
        }

        public void setDietPlanId(String dietPlanId) {
            this.dietPlanId = dietPlanId;
        }

        public String getMenu() {
            return menu;
        }

        public void setMenu(String menu) {
            this.menu = menu;
        }

        public String getDietPlan() {
            return dietPlan;
        }

        public void setDietPlan(String dietPlan) {
            this.dietPlan = dietPlan;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public boolean isDeleted() {
            return isDeleted;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}
