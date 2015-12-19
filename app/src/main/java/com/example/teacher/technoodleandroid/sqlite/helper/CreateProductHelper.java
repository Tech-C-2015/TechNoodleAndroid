package com.example.teacher.technoodleandroid.sqlite.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.teacher.technoodleandroid.sqlite.UserHelper;


/**
 * Created by teacher on 2015/11/21.
 */
public class CreateProductHelper extends SQLiteOpenHelper {

    public CreateProductHelper(Context context){
        super(context,"sample",null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            //db.execSQL("drop table Users");
            db.execSQL(UserHelper.createSQL);

            db.execSQL("insert into Users(name,address,tel) values('test1','saitama','080-1111-1111');");
            db.execSQL("insert into Users(name,address,tel) values('test2','saitama','080-1111-1111');");
            db.execSQL("insert into Users(name,address,tel) values('test3','saitama','080-1111-1111');");
            db.execSQL("insert into Users(name,address,tel) values('test4','saitama','080-1111-1111');");
        }catch(Exception e){
            // noop
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Users");
        onCreate(db);
    }
}
