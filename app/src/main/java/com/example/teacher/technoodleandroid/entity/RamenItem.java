package com.example.teacher.technoodleandroid.entity;

/**
 * Created by student on 2016/01/30.
 */
public class RamenItem {

    private int _id;
    private String _ramenImage;
    private String _name;
    private String _address_pre;
    private String _address_reg;
    private String _nearest_sta;
    private String _tag;


    public int getId(){ return _id;}

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

    public RamenItem(int id, String name, String address_pre,  String address_reg , String nearest_sta, String tag, String ramenImage) {
        _name = name;
        _address_pre = address_pre;
        _address_reg = address_reg;
        _nearest_sta = nearest_sta;
        _tag = tag;
        _ramenImage = ramenImage;

    }
}