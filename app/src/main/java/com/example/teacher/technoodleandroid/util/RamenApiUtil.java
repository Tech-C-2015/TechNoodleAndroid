package com.example.teacher.technoodleandroid.util;

import android.app.Activity;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.example.teacher.technoodleandroid.AppController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by student on 2016/01/31.
 */
public class RamenApiUtil {


    private Activity mActivity;

    private String mResponse;

    private Map<String, String> params = new HashMap<>();


    public RamenApiUtil(Activity activity){
        mActivity = activity;
    }

    public void GetRequest(String url){

        AppController app = (AppController) mActivity.getApplication();

        GetRequest request = new GetRequest(new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //実際のjsonデータはresponseに格納されてる
                mResponse = response;

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }, getParameter(), url);
        app.mRequestQueue.add(request);
    }

    public String[] getResponse(){

        if(null == mResponse){
            return  null;
        }else{


        }
        String[] s = {"lll"};
        return  s ;
    }

    public void setName(String name){
        setParameter("name", name);
    }

    public void setPref(String prefecture){
        setParameter("prefecture", prefecture);

    }

    public void setReg(String region){
        setParameter("region", region);

    }

    public void setSta( String station){
        setParameter("station", station);

    }

    public void setTag( String tag){
        setParameter("tag", tag);

    }

    public void setLimit(int limit){
        setParameter("limit", limit);

    }

    public void setOffset(int offset){
        setParameter("offset", offset);
    }


    private void setParameter(String key, Object obj) {
        params.put(key, obj.toString());
    }

    private Map<String, String> getParameter() {
        return params;
    }


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
