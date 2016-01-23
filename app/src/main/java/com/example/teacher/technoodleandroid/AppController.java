package com.example.teacher.technoodleandroid;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by student on 16/01/23.
 */
public class AppController extends Application {
    public RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
       mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }
}
