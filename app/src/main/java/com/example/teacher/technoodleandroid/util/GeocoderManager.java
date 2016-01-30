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
            for (int i = 0; (buf = address.getAddressLine(i)) != null; i++){
                strbuid.append("address.getAddressLine("+i+"):"+buf+"\n");
            }

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
