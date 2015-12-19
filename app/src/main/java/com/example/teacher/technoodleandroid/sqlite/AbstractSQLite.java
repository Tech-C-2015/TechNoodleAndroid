package com.example.teacher.technoodleandroid.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.teacher.technoodleandroid.sqlite.helper.CreateProductHelper;

/**
 * Created by teacher on 2015/11/28.
 */
public abstract class AbstractSQLite {

    protected CreateProductHelper createProductHelper = null;
    protected SQLiteDatabase sqliteDatabase = null;

    public AbstractSQLite(Context context){
        createProductHelper = new CreateProductHelper(context);
    }

    public void startTransaction(){
        sqliteDatabase = createProductHelper.getWritableDatabase();
        sqliteDatabase.beginTransaction();
    }

    public void commitTransaction(){
        sqliteDatabase.setTransactionSuccessful();
    }

    public void endTransaction(){
        sqliteDatabase.endTransaction();
    }

    public void close(){
        sqliteDatabase.close();
    }
}
