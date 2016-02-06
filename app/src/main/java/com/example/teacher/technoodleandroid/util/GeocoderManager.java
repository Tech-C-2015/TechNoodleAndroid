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
    final static int FULL_ADDRESS = 0;
    final static int PREFECTURE = 1;
    final static int SHICYOSON = 2;
    final static int GUN = 3;
    final static int TYOME = 4;
    final static int BANCHI = 5;
    final static int GOU = 6;

    public GeocoderManager(Context context){
        mGeocoder = new Geocoder(context, Locale.JAPAN);
    }

    public String[] getAddressFromGeocode(double latitude, double longitude){

        String address_string[] = new String[7];

        try {
            mList_address = mGeocoder.getFromLocation(latitude, longitude, 5);
        }catch(IOException e){

        }
        //ジオコーディングに成功したらStringへ
        if (!mList_address.isEmpty()){

            Address address = mList_address.get(0);


            address_string[FULL_ADDRESS] = address.getAddressLine(1);
            address_string[PREFECTURE] = address.getAdminArea();
            address_string[SHICYOSON] = address.getLocality();
            address_string[GUN] = address.getSubAdminArea();
            address_string[TYOME] = address.getThoroughfare();
            address_string[BANCHI] = address.getSubThoroughfare();
            address_string[GOU] = address.getFeatureName();

        }

        //失敗（Listが空だったら）
        else {
            address_string[0] = "failed to get address from geocode!";
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
