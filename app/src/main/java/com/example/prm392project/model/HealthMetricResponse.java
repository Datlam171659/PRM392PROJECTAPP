package com.example.prm392project.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class HealthMetricResponse {

    @SerializedName("results")
    private List<HealthMetric> results;

    @SerializedName("totalRecords")
    private int totalRecords;

    @SerializedName("isSuccess")
    private boolean isSuccess;

    @SerializedName("message")
    private String message;

    public List<HealthMetric> getResults() {
        return results;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public String getMessage() {
        return message;
    }

    public static class HealthMetric {
        @SerializedName("bloodSugar")
        private int bloodSugar;

        @SerializedName("uricAcid")
        private int uricAcid;

        @SerializedName("weight")
        private int weight;

        @SerializedName("bloodPressure")
        private String bloodPressure;

        public int getBloodSugar() {
            return bloodSugar;
        }

        public int getUricAcid() {
            return uricAcid;
        }

        public int getWeight() {
            return weight;
        }

        public String getBloodPressure() {
            return bloodPressure;
        }
    }
}
