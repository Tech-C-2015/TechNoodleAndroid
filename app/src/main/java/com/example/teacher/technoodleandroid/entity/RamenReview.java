package com.example.teacher.technoodleandroid.entity;

/**
 * Created by 慎也 on 2016/02/03.
 */
public class RamenReview {

    private String _id;
    private String _review;
    private String user_name;
    private String shop_id;
    private String created_at;
    private String updated_at;
    private String rank;

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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getShop_id() {
        return shop_id;
    }

    public void setShop_id(String shop_id) {
        this.shop_id = shop_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }
}
