package com.example.prm392project.model;

public class AdminModel {
    public interface OnFinishedListener {
        void onFinished(String result);
    }

    public void getAdminStats(OnFinishedListener listener) {
        // Simulate a data fetch or call an API
        listener.onFinished("Data Fetched");
    }
}

