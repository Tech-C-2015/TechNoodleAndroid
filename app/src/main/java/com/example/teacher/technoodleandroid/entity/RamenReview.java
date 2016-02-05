package com.example.teacher.technoodleandroid.entity;

/**
 * Created by 慎也 on 2016/02/03.
 */
public class RamenReview {

    //ラーメン店Review情報を格納するクラス
    private String _id;
    private String _review;
    private String _shop_id;
    private String _created_at;
    private String _updated_at;
    private String _rank;
    private String _user_name;

    public RamenReview() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_review() {
        return _review;
    }

    public void set_review(String _review) {
        this._review = _review;
    }

    public String get_shop_id() {
        return _shop_id;
    }

    public void set_shop_id(String _shop_id) {
        this._shop_id = _shop_id;
    }

    public String get_created_at() {
        return _created_at;
    }

    public void set_created_at(String _created_at) {
        this._created_at = _created_at;
    }

    public String get_updated_at() {
        return _updated_at;
    }

    public void set_updated_at(String _updated_at) {
        this._updated_at = _updated_at;
    }

    public String get_rank() {
        return _rank;
    }

    public void set_rank(String _rank) {
        this._rank = _rank;
    }

    public String get_user_name() {
        return _user_name;
    }

    public void set_user_name(String _user_name) {
        this._user_name = _user_name;
    }
}
