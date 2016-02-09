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
    final static double gps_to_metar = 0.000277778;
    final static double latitude_const = 30.8184;
    final static double longitude_const = 25.2450;
    final static public int ORIGINAL = 0;
    final static public int NORTH = 1;
    final static public int SOUTH = 2;
    final static public int WEST = 3;
    final static public int EAST = 4;

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



    public String[][] getAreaAddressFromGeocode(double latitude, double longitude, int area_as_metar){



        double to_north_300m_latitude = latitude + (area_as_metar / latitude_const * gps_to_metar);
        double to_north_300m_longitude = longitude;
        double to_south_300m_latitude = latitude - (area_as_metar / latitude_const * gps_to_metar);
        double to_south_300m_longitude = longitude;
        double to_west_300m_latitude = latitude;
        double to_west_300m_longitude = longitude + (area_as_metar / longitude_const * gps_to_metar);
        double to_east_300m_latitude = latitude;
        double to_east_300m_longitude = longitude - (area_as_metar / longitude_const * gps_to_metar);

        String[] original_gps2address = getAddressFromGeocode(latitude, longitude);
        String[] to_north_gps2address = getAddressFromGeocode(to_north_300m_latitude, to_north_300m_longitude);
        String[] to_south_gps2address = getAddressFromGeocode(to_south_300m_latitude, to_south_300m_longitude);
        String[] to_west_gps2address = getAddressFromGeocode(to_west_300m_latitude, to_west_300m_longitude);
        String[] to_east_gps2address = getAddressFromGeocode(to_east_300m_latitude, to_east_300m_longitude);

        String[][] area_address = new String[][]{
                original_gps2address, to_north_gps2address, to_south_gps2address, to_west_gps2address, to_east_gps2address
        };

        return area_address;

    }
}
