package com.example.prm392project.model;

import java.util.List;

public class LoginResponse {
    private Result result;
    private String token;
    private String expiration;
    private boolean isSuccess;
    private String message;

    // Getters and setters
    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration(String expiration) {
        this.expiration = expiration;
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
        private String id;
        private String username;
        private String password;
        private String fullName;
        private String imageUrl;
        private String email;
        private String dob;
        private String address;
        private String gender;
        private String phone;
        private Integer diseaseType;
        private int userType;
        private Integer userRole;
        private HealthMetric healthMetric;
        private List<DietPlan> dietPlans;
        private List<Notification> notifications;
        private String createdBy;
        private String createdDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;
        private boolean isDeleted;

        // Getters and setters for result fields

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Integer getDiseaseType() {
            return diseaseType;
        }

        public void setDiseaseType(Integer diseaseType) {
            this.diseaseType = diseaseType;
        }

        public int getUserType() {
            return userType;
        }

        public void setUserType(int userType) {
            this.userType = userType;
        }

        public Integer getUserRole() {
            return userRole;
        }

        public void setUserRole(Integer userRole) {
            this.userRole = userRole;
        }

        public HealthMetric getHealthMetric() {
            return healthMetric;
        }

        public void setHealthMetric(HealthMetric healthMetric) {
            this.healthMetric = healthMetric;
        }

        public List<DietPlan> getDietPlans() {
            return dietPlans;
        }

        public void setDietPlans(List<DietPlan> dietPlans) {
            this.dietPlans = dietPlans;
        }

        public List<Notification> getNotifications() {
            return notifications;
        }

        public void setNotifications(List<Notification> notifications) {
            this.notifications = notifications;
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

    public static class DietPlan {
        private String userId;
        private String dateAssigned;
        private String period;
        private int status;
        private Object user;
        private List<Object> menuDietPlans;
        private String id;
        private String createdBy;
        private String createdDate;
        private String lastUpdatedBy;
        private String lastUpdatedDate;
        private boolean isDeleted;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setDateAssigned(String dateAssigned) {
            this.dateAssigned = dateAssigned;
        }

        public void setPeriod(String period) {
            this.period = period;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public void setUser(Object user) {
            this.user = user;
        }

        public void setMenuDietPlans(List<Object> menuDietPlans) {
            this.menuDietPlans = menuDietPlans;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setCreatedBy(String createdBy) {
            this.createdBy = createdBy;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }

        public void setLastUpdatedBy(String lastUpdatedBy) {
            this.lastUpdatedBy = lastUpdatedBy;
        }

        public void setLastUpdatedDate(String lastUpdatedDate) {
            this.lastUpdatedDate = lastUpdatedDate;
        }

        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }

        public String getDateAssigned() {
            return dateAssigned;
        }

        public String getPeriod() {
            return period;
        }

        public int getStatus() {
            return status;
        }

        public Object getUser() {
            return user;
        }

        public List<Object> getMenuDietPlans() {
            return menuDietPlans;
        }

        public String getId() {
            return id;
        }

        public String getCreatedBy() {
            return createdBy;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public String getLastUpdatedBy() {
            return lastUpdatedBy;
        }

        public String getLastUpdatedDate() {
            return lastUpdatedDate;
        }

        public boolean isDeleted() {
            return isDeleted;
        }
// Getters and setters for DietPlan fields
        // ...
    }

    public static class Notification {
        // Define Notification fields here if needed
        // Getters and setters for Notification fields
        // ...
    }

    public static class HealthMetric {
        private String id;

        public String getId() {
            return id;
        }

        public String getBloodSugar() {
            return bloodSugar;
        }

        public String getUricAcid() {
            return uricAcid;
        }

        public String getWeight() {
            return weight;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }

        public String getNote() {
            return note;
        }

        private String bloodSugar;
        private String uricAcid;
        private String weight;
        private String bloodPressure;
        private String note;

        public void setId(String id) {
            this.id = id;
        }

        public void setBloodSugar(String bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public void setUricAcid(String uricAcid) {
            this.uricAcid = uricAcid;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public void setBloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
        }

        public void setNote(String note) {
            this.note = note;
        }
// Getters and setters for HealthMetric fields
        // ...
    }
}
