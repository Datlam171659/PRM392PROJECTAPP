package com.example.prm392project.model;

public class HealthMetric {
    private String userId;
    private String dateRecorded;
    private int bloodSugar;
    private int uricAcid;
    private int weight;
    private String bloodPressure;
    private String note;

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getDateRecorded() { return dateRecorded; }
    public void setDateRecorded(String dateRecorded) { this.dateRecorded = dateRecorded; }

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

