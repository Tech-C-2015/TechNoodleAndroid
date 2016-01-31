package com.example.teacher.technoodleandroid.util;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import java.util.Map;

public class GetRequest extends StringRequest {

    private Map<String, String> mParams;
    //API　URLの設置
    private static final String URL = "http://133.130.106.164/Tech-Noodle-Api/public/noodle/list";

    public GetRequest(Response.Listener<String> listener, Response.ErrorListener errorListener, Map<String, String> mParams, String url) {
        super(Method.GET, url, listener, errorListener);
        this.mParams = mParams;
    }

    @Override
    public String getUrl() {
        return super.getUrl() + makeGetParameter();
    }

    /*
    パラメータの作成
     */
    private String makeGetParameter() {
        String param = "?";
        boolean isFirst = true;

        for (Map.Entry<String, String> entry : mParams.entrySet()) {
            if (isFirst) {
                param += entry.getKey() + "=" + entry.getValue();
            } else {
                param += "&" + entry.getKey() + "=" + entry.getValue();
            }
        }
        return param;
    }
}
