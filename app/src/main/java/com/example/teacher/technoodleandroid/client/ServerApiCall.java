package com.example.teacher.technoodleandroid.client;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.teacher.technoodleandroid.AppController;
import com.example.teacher.technoodleandroid.entity.RamenItem;
import com.example.teacher.technoodleandroid.util.GetRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ym on 2016/01/31.
 */
public class ServerApiCall {
    public ServerApiCall() {
    }

    public interface Listener<T> {
        void onFinish(T obj);
    }

    private static final String RamenItemURL = "http://133.130.106.164/Tech-Noodle-Api/public/noodle/list";

    public void callStoreList(final Listener l, AppController app,String name) {
        Map<String, String> params = new HashMap<>();
        params.put("name",name);
        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                List<RamenItem> itemLst = new ArrayList<RamenItem>();
                try {

                    JSONObject json = new JSONObject(response);
                    JSONObject info = json.getJSONObject("info");

                    while (info.keys().hasNext()) {
                        RamenItem item = new RamenItem();
                        String key = info.keys().next();
                        JSONObject data = info.getJSONObject(key);
                        // TODO 追加してください
                        item.set_id(data.getString("id"));
                        item.set_ramenImage(data.getString("image"));
                        item.set_name(data.getString("name"));
                        itemLst.add(item);
                        break; // TODO 仮
                    }

                    l.onFinish(itemLst);

                } catch (Exception e) {
                    l.onFinish(itemLst);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }, params, RamenItemURL);
        app.mRequestQueue.add(request);
    }

}
