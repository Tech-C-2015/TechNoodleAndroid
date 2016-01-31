package com.example.teacher.technoodleandroid.client;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.teacher.technoodleandroid.AppController;
import com.example.teacher.technoodleandroid.util.GetRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ym on 2016/01/31.
 */
public class ServerApiCall {

    public void callStoreList(AppController app){

        Map<String, String> params = new HashMap<>();
        params.put("name", "");

        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                return;
            }
        }, params);
        app.mRequestQueue.add(request);
    }

}
