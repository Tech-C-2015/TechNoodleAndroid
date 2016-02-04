package com.example.teacher.technoodleandroid.client;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.teacher.technoodleandroid.AppController;
import com.example.teacher.technoodleandroid.entity.RamenItem;
import com.example.teacher.technoodleandroid.entity.RamenReview;
import com.example.teacher.technoodleandroid.util.GetRequest;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
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

    private static final String RamenReviewURL = "http://133.130.106.164/Tech-Noodle-Api/public/review/review";

    private static final String RamenReviewCreateURL  ="http://133.130.106.164/Tech-Noodle-Api/public/review/create";

    public void callStoreList(final Listener l, AppController app, Map<String, String> params) {
        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {




                    List<RamenItem> itemLst = new ArrayList<RamenItem>();
                    try {

                        JSONObject json = new JSONObject(response);
                        JSONObject info = json.getJSONObject("info");
                        Iterator<String> keys = info.keys();
                        while (keys.hasNext()) {
                            RamenItem item = new RamenItem();
                            String key = keys.next();
                            JSONObject data = info.getJSONObject(key);
                            // TODO 追加してください
                            item.set_id(data.getString("id"));
                            item.set_ramenImage(data.getString("image"));
                            item.set_name(data.getString("name"));
                            item.set_address_pre(data.getString("prefecture"));
                            item.set_address_reg(data.getString("region"));
                            item.set_nearest_sta(data.getString("station"));
                            item.set_tag(data.getString("tag"));
                            item.set_address_add(data.getString("address"));
                            item.set_tel(data.getString("tel"));

                            itemLst.add(item);
/*
                            name => 店舗名
                            prefecture => 都道府県
                            region => 市区村町
                            station => 駅名
                            tag => ジャンル
                            limit => 件数　(default:60件)
                                offset => 0から始める (default:0)
                                */

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


    public void CallStoreReview(final Listener l, AppController app, Map<String, String> params){

        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                List<RamenReview> reviewLst = new ArrayList<RamenReview>();
                try {

                    JSONObject json = new JSONObject(response);
                    JSONObject info = json.getJSONObject("info");

                    Iterator<String> keys = info.keys();
                    while (keys.hasNext()) {
                        RamenReview item = new RamenReview();
                        String key = keys.next();
                        JSONObject data = info.getJSONObject(key);
                        item.set_id(data.getString("id"));
                        item.set_review(data.getString("review"));
                        item.setShop_id(data.getString("shop_id"));
                        item.setUser_name(data.getString("user_name"));
                        item.setCreated_at(data.getString("created_at"));
                        item.setUpdated_at(data.getString("updated_at"));
                        item.setRank(data.getString("rank"));
                        reviewLst.add(item);

                    }

                    l.onFinish(reviewLst);

                } catch (Exception e) {
                    l.onFinish(reviewLst);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }, params, RamenReviewURL);
        app.mRequestQueue.add(request);

    }

    public void AddStoreReview(final Listener l, AppController app, Map<String, String> params){
        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                l.onFinish(response);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }, params, RamenReviewCreateURL);
        app.mRequestQueue.add(request);

    }

}
