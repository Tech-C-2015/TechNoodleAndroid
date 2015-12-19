package com.example.teacher.technoodleandroid.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.example.teacher.technoodleandroid.sqlite.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by teacher on 2015/11/28.
 */
public class UserHelper extends AbstractSQLite {

    // table
    private String _table = "Users";

    // column list
    private String _colID = "id";
    private String _colName = "name";
    private String _colAddress = "address";
    private String _colTel = "tel";

    public static final String createSQL = "CREATE TABLE [Users] (" +
            "[id] INTEGER PRIMARY KEY AUTOINCREMENT," +
            "[name] VARCHAR(20)," +
            "[address] VARCHAR(100)," +
            "[tel] VARCHAR(29)" +
            ");";

    public UserHelper(Context context) {
        super(context);
    }

    /**
     * Select実行
     * @param where
     * @return
     */
    public List<UserEntity> select(String where){

        sqliteDatabase = createProductHelper.getReadableDatabase();

        List<UserEntity> list = new ArrayList<>();

        String columns[] = {_colID,_colName,_colAddress,_colTel};
        Cursor cursor = sqliteDatabase.query(
                _table,columns,where,null,null,null,_colID);

        while(cursor.moveToNext()){
            UserEntity entity = new UserEntity();
            entity.setId(cursor.getInt(0));
            entity.setName(cursor.getString(1));
            entity.setAddress(cursor.getString(2));
            entity.setTel(cursor.getString(3));
            list.add(entity);
        }

        return list;
    }

    /**
     * Insert実行
     * @param name
     * @param address
     * @param tel
     * @return
     */
    public boolean insert(String name,String address,String tel){
        ContentValues value = new ContentValues();
        value.put(_colName, name);
        value.put(_colAddress, address);
        value.put(_colTel, tel);
        try {
            sqliteDatabase.insert(_table, null, value);
        }catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean update(){
        return true;
    }

    public boolean delete(){
        return true;
    }



}
