package com.example.teacher.technoodleandroid.entity;

/**
 * Created by student on 2016/01/30.
 */
public class RamenItem {

    //ラーメン店情報を格納するクラス
    private String _id;
    private String _ramenImage;
    private String _name;
    private String _prefecture;
    private String _region;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String get_ramenImage() {
        return _ramenImage;
    }

    public void set_ramenImage(String _ramenImage) {
        this._ramenImage = _ramenImage;
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_prefecture() {
        return _prefecture;
    }

    public void set_prefecture(String _prefecture) {
        this._prefecture = _prefecture;
    }

    public String get_region() {
        return _region;
    }

    public void set_region(String _region) {
        this._region = _region;
    }

    public String get_station() {
        return _station;
    }

    public void set_station(String _station) {
        this._station = _station;
    }

    public String get_tel() {
        return _tel;
    }

    public void set_tel(String _tel) {
        this._tel = _tel;
    }

    public String get_address() {
        return _address;
    }

    public void set_address(String _address) {
        this._address = _address;
    }

    public String get_tag() {
        return _tag;
    }

    public void set_tag(String _tag) {
        this._tag = _tag;
    }

    private String _station;
    private String _tel;
    private String _address;
    private String _tag;

    public RamenItem() {
    }


}