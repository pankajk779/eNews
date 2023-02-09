package com.example.news.controller;

import android.app.Application;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        VolleyClient.getInstance().createRequestQueue(this);
    }
}
