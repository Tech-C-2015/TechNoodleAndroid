package com.example.teacher.technoodleandroid.sqlite.entity;

/**
 * Created by teacher on 2015/11/28.
 */
public class UserEntity {
    private int id;
    private String name;
    private String address;
    private String tel;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
