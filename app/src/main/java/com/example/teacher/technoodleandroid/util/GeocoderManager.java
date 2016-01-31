package com.example.teacher.technoodleandroid.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by student on 2016/01/30.
 */
public class GeocoderManager {

    private Geocoder mGeocoder = null;
    private List<Address> mList_address = null;

    public GeocoderManager(Context context){
        mGeocoder = new Geocoder(context, Locale.JAPAN);
    }

    public String getAddressFromGeocode(double latitude, double longitude){

        String address_string = null;

        try {
            mList_address = mGeocoder.getFromLocation(latitude, longitude, 5);
        }catch(IOException e){

        }
        //ジオコーディングに成功したらStringへ
        if (!mList_address.isEmpty()){

            Address address = mList_address.get(0);
            StringBuilder strbuid = new StringBuilder();

            //addressをStringへ
            String buf;
            String[] str_add = new String[3];

            str_add[0] = address.getAdminArea();
            //str_add[1] = address.get

            strbuid.append(address.getAddressLine(1));
                strbuid.append(address.getAdminArea());//都道府県
                strbuid.append(address.getFeatureName());//号
  //          strbuid.append(address.getPremises());//不明
            strbuid.append(address.getSubLocality());//町名
            strbuid.append(address.getCountryName());////国名
            strbuid.append(address.getExtras());
//          strbuid.append(address.getSubAdminArea());//不明
//            strbuid.append(address.getSubThoroughfare());//不明
            strbuid.append(address.getThoroughfare());//丁目




            address_string = strbuid.toString();

        }

        //失敗（Listが空だったら）
        else {

        }

        return address_string;

    }

    public Address getGeocodeFromAddress(String address){

        try {
            mList_address = mGeocoder.getFromLocationName(address, 5);
        }catch(IOException e) {

        }

        Address geocode = null;

        //ジオコーディングに成功したらStringへ
        if (!mList_address.isEmpty()){

            geocode = mList_address.get(0);


        }

        return geocode;
    }



}
