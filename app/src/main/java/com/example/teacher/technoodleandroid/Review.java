package com.example.teacher.technoodleandroid;

/**
 * Created by yuto on 2016/02/10.
 */
public class Review {
    String taste;
    String topping;
    String price;
    String comment;

    public Review(String taste,String topping,String price,String comment){
        this.taste = taste;
        this.topping = topping;
        this.price = price;
        this.comment =comment;
    }

    public void setTaste(String taste) {
        this.taste = taste;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTaste(){
        return taste;
    }
    public String getTopping(){
        return topping;
    }
    public String getPrice(){
        return price;
    }
    public String getComment(){
        return comment;
    }
}
