package com.example.prm392project.model;

public class HealthMetricRequest {
    private String id;
    private String userId;
    private int bloodSugar;
    private int uricAcid;
    private int weight;
    private String bloodPressure;
    private String note;

    public HealthMetricRequest(String id, String userId, int bloodSugar, int uricAcid,
                               int weight, String bloodPressure, String note) {
        this.id = id;
        this.userId = userId;
        this.bloodSugar = bloodSugar;
        this.uricAcid = uricAcid;
        this.weight = weight;
        this.bloodPressure = bloodPressure;
        this.note = note;
    }

    // Getters and setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public int getBloodSugar() { return bloodSugar; }
    public void setBloodSugar(int bloodSugar) { this.bloodSugar = bloodSugar; }

    public int getUricAcid() { return uricAcid; }
    public void setUricAcid(int uricAcid) { this.uricAcid = uricAcid; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }

    public String getBloodPressure() { return bloodPressure; }
    public void setBloodPressure(String bloodPressure) { this.bloodPressure = bloodPressure; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}
