package com.example.teacher.technoodleandroid.entity;

/**
 * Created by student on 2016/01/30.
 */
public class RamenItem {

    private String _id;
    private String _ramenImage;
    private String _name;
    private String _address_pre;
    private String _address_reg;
    private String _nearest_sta;

    public String get_tel() {
        return _tel;
    }

    public void set_tel(String _tel) {
        this._tel = _tel;
    }

    private String _tel;

    public String get_address_add() {
        return _address_add;
    }

    public void set_address_add(String _address_add) {
        this._address_add = _address_add;
    }

    private String _address_add;
    private String _tag;

    public String getId(){ return _id;}

    public String getName() {
        return _name;
    }

    public String getRamenImage(){
        return _ramenImage;
    }


    public String getAddressPre(){ return _address_pre; }

    public String getAddressReg(){ return _address_reg; }

    public String getNearestSta(){ return _nearest_sta; }

    public String getTag(){ return _tag; }

    public RamenItem(){}


    public void set_id(String _id) {
        this._id = _id;
    }

    public void set_tag(String _tag) {
        this._tag = _tag;
    }

    public void set_nearest_sta(String _nearest_sta) {
        this._nearest_sta = _nearest_sta;
    }

    public void set_address_reg(String _address_reg) {
        this._address_reg = _address_reg;
    }

    public void set_address_pre(String _address_pre) {
        this._address_pre = _address_pre;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public void set_ramenImage(String _ramenImage) {
        this._ramenImage = _ramenImage;
    }
}