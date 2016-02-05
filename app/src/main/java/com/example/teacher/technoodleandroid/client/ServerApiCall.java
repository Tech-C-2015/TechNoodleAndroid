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

    //ラーメン店情報取得用UR
    private static final String RamenItemURL = "http://133.130.106.164/Tech-Noodle-Api/public/noodle/list";

    //Review取得用URL
    private static final String RamenReviewURL = "http://133.130.106.164/Tech-Noodle-Api/public/review/review";

    //Review投稿用URL
    private static final String RamenReviewCreateURL  ="http://133.130.106.164/Tech-Noodle-Api/public/review/create";

    //ラーメン店情報取得メソッド
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
                            // 取得したラーメン店情報をRamenItemオブジェクト
                            // にSet
                            item.set_id(data.getString("id"));
                            item.set_ramenImage(data.getString("image"));
                            item.set_name(data.getString("name"));
                            item.set_prefecture(data.getString("prefecture"));
                            item.set_region(data.getString("region"));
                            item.set_station(data.getString("station"));
                            item.set_tag(data.getString("tag"));
                            item.set_address(data.getString("address"));
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


    //ラーメン店Reviewを取得
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
                        //取得した譲歩をRamenReviewオブジェクトにSet
                        item.set_id(data.getString("id"));
                        item.set_review(data.getString("review"));
                        item.set_shop_id(data.getString("shop_id"));
                        item.set_user_name(data.getString("user_name"));
                        item.set_created_at(data.getString("created_at"));
                        item.set_updated_at(data.getString("updated_at"));
                        item.set_rank(data.getString("rank"));
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

    //ラーメン店Review投稿用メソッド
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
