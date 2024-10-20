package com.example.prm392project.model;

public class LoginResponse {
    private Result result;
    private String token;
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

    // Class to hold the "result" object
    public static class Result {
        private String id;
        private String username;
        private String fullName;
        private String email;
        private String imageUrl;
        private String address;
        private int diseaseType;
        private String[] dietPlans;
        private HealthMetric healthMetric;

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

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public int getDiseaseType() {
            return diseaseType;
        }

        public void setDiseaseType(int diseaseType) {
            this.diseaseType = diseaseType;
        }

        public String[] getDietPlans() {
            return dietPlans;
        }

        public void setDietPlans(String[] dietPlans) {
            this.dietPlans = dietPlans;
        }

        public HealthMetric getHealthMetric() {
            return healthMetric;
        }

        public void setHealthMetric(HealthMetric healthMetric) {
            this.healthMetric = healthMetric;
        }
    }

    public static class HealthMetric {
        private String id;
        private String bloodSugar;
        private String uricAcid;
        private String weight;
        private String bloodPressure;
        private String note;

        // Getters và setters cho HealthMetric
        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBloodSugar() {
            return bloodSugar;
        }

        public void setBloodSugar(String bloodSugar) {
            this.bloodSugar = bloodSugar;
        }

        public String getUricAcid() {
            return uricAcid;
        }

        public void setUricAcid(String uricAcid) {
            this.uricAcid = uricAcid;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }

        public void setBloodPressure(String bloodPressure) {
            this.bloodPressure = bloodPressure;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }
    }
}
