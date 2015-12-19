package com.example.teacher.technoodleandroid.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Androidの内部データ管理（preference）クラス
 */
public class Preference {

    private final static String PREF_KEY_LOGIN_PASSWORD
            = "PREF_KEY_LOGIN_PASSWORD";

//    public static boolean putLoginPassword(Context context,
//                                           String pass){
//        return savePreference(context,PREF_KEY_LOGIN_PASSWORD,pass);
//    }
//    public static String getLoginPassword(Context context){
//        return loadPreference(context,PREF_KEY_LOGIN_PASSWORD, "");
//    }

    public static String loadPreference(Context context,
                                        String key,String defValue){
        SharedPreferences preference =
                context.getSharedPreferences("pref",
                        Context.MODE_PRIVATE);
        return preference.getString(key, defValue);
    }

    public static boolean savePreference
            (Context context,String key,Object value){
        SharedPreferences preference =
                context.getSharedPreferences("pref",
                        Context.MODE_PRIVATE);
        SharedPreferences.Editor editor =
                preference.edit();

        if(value instanceof String)
            editor.putString(key, (String)value);
        else if(value instanceof Integer)
            editor.putInt(key, (Integer)value);
        else if(value instanceof Boolean)
            editor.putBoolean(key, (Boolean)value);
        else if(value instanceof Long)
            editor.putLong(key, (Long)value);
        else if(value instanceof Float)
            editor.putFloat(key, (Float)value);

        return editor.commit();
    }

}
