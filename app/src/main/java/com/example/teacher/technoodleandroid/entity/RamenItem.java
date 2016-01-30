package com.example.teacher.technoodleandroid.entity;

import android.widget.ImageView;

/**
 * Created by student on 2016/01/30.
 */
public class RamenItem {

    private ImageView _ramenImage;
    private String _name;


    public String getName() {
        return _name;
    }

    public ImageView getRamenImage(){
        return _ramenImage;
    }

    public RamenItem(String name,  ImageView ramenImage) {
        _name = name;
        _ramenImage = ramenImage;

    }
}