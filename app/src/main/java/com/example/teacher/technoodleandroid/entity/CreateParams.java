package com.example.teacher.technoodleandroid.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 慎也 on 2016/02/03.
 * ServerApiCall使用時のGetパラメータ作成用クラス
 */
public class CreateParams {

    private Map<String, String> params;
    private String type;
    private final static String TYPE_ITEM = "item";
    private final static String TYPE_REVIEW = "review";

    public CreateParams(){
    }

    //RamenItem取得時のパラメータ作成メソッド
    public void CreateRamenItemParam(){
        params = new HashMap<String, String>();
        type = TYPE_ITEM;

    }

    //Map<String, String>のオブジェクトを返す
    public Map<String, String> getParams(){
        return params;
    }

    public void setName(String name){

        if( CheckTypeItem()) {
            setParameter("name", name);
        }

    }

    public void setPrefecture(String prefecture){
        if( CheckTypeItem()) {
            setParameter("prefecture", prefecture);
        }
    }

    public void setAddress(String address){
        if(CheckTypeItem()){
            setParameter("address", address);
        }
    }
    public void setRegion(String region){
        if( CheckTypeItem()) {
            setParameter("region", region);
        }

    }

    public void setStation( String station){
        if( CheckTypeItem()) {
            setParameter("station", station);
        }


    }

    public void setTag( String tag){
        if( CheckTypeItem()) {
            setParameter("tag", tag);
        }

    }

    public void setLimit(String limit){
        if( CheckTypeItem()) {
            setParameter("limit", limit);
        }

    }

    public void setOffset(String offset){
        if( CheckTypeItem()) {
            setParameter("offset", offset);
        }
    }

    //ラーメン店Review投稿用パラメータ作成
    public void CreateRamenReviewParam() {
        params = new HashMap<String, String>();
        type = TYPE_REVIEW;
    }


    public void setShopId(String shop_id){
        if(CheckTypeReview()){
            setParameter("id", shop_id);
        }

    }

    public void setReview(String review){
        if(CheckTypeReview()){
            setParameter("review", review);
        }

    }

    public void setUserName(String user_name){
        if(CheckTypeReview()){
            setParameter("user_name", user_name);

        }

    }

    public void setRank(String rank){
        if(CheckTypeReview()){
            setParameter("rank", rank);

        }

    }

    private void setParameter(String key, Object obj) {
        params.put(key, obj.toString());
    }

    private boolean CheckTypeItem(){
        if(type.equals(TYPE_ITEM)){
            return true;
        }else{
            return false;
        }

    }


    private boolean CheckTypeReview(){
        if(type.equals(TYPE_REVIEW)){
            return true;
        }else{
            return false;
        }
    }


}
