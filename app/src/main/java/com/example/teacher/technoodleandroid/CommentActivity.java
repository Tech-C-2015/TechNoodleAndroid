package com.example.teacher.technoodleandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuto on 2016/02/09.
 */
public class CommentActivity extends ActionBarActivity {
    static List<Review> reviewList = new ArrayList<Review>();
    static ReviewAdapter adapter;
    ListView reviewListView;

    String strTaste;
    String strTopping;
    String strPrice;
    String strComment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment);
        findViews();
        setAdapters();

        // 押されたらcomment_dialogを起動するボタン
        final Button comment_button = (Button)findViewById(R.id.comment_button);
        comment_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                commentDialog();
            }
        });
        // mapボタンを押した時の処理
        final Button map_button = (Button)findViewById(R.id.map_button);
        map_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // 明示的intent でMAP起動
                Uri uri = Uri.parse("geo:0,0?q=" + "");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });

    }
    protected void findViews(){
        reviewListView = (ListView)findViewById(R.id.comment_list);
    }
    protected void setListeners(){
    }
    protected void setAdapters(){
        adapter = new ReviewAdapter();
        reviewListView.setAdapter(adapter);
    }
    protected void getStringReview(){

    }
    protected void addReview(){
        reviewList.add(new Review(strTaste,strTopping,strPrice,strComment));
        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    // コメントダイアログ設定
    private void commentDialog(){
        // カスタムビューを設定
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        final View layout = inflater.inflate(R.layout.comment_dialog, (ViewGroup)findViewById(R.id.comment_dialog));

        // アラートダイアログ を生成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.comment_dialog_title);
        builder.setView(layout);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // オブジェクト取得
                Spinner taste = (Spinner)layout.findViewById(R.id.spi_taste);
                Spinner tooping = (Spinner)layout.findViewById(R.id.spi_topping);
                Spinner price = (Spinner)layout.findViewById(R.id.spi_price);
                EditText comment = (EditText)layout.findViewById(R.id.noodle_comment);

                // オブジェクトの値を取得してStringに
                strTaste = taste.getSelectedItem().toString();
                strTopping = tooping.getSelectedItem().toString();
                strPrice = price.getSelectedItem().toString();
                strComment = comment.getText().toString();

                addReview();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Cancel ボタン処理

            }
        });

        // 表示
        builder.create().show();
    }
    // Toast出すだけ
    private void showToast(String s){
        Toast.makeText(this, "toast+" + s, Toast.LENGTH_LONG).show();
    }

    // comment_list
    private void comment_add(String s){
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        adapter.add(s);

        // リストビューにデータを設定
        ListView listView1 = (ListView)findViewById(R.id.comment_list);
        listView1.setAdapter(adapter);
    }

    public void commentView(){

    }
    private class ReviewAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return reviewList.size();
        }

        @Override
        public Object getItem(int position) {
            return reviewList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            TextView tvTaste;
            TextView tvToping;
            TextView tvPrice;
            TextView tvComment;
            View v = convertView;

            if (v == null){
                LayoutInflater layoutInflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = layoutInflater.inflate(R.layout.comment_row,null);
            }

            Review review = (Review)getItem(position);
            if (review != null){
                tvTaste = (TextView)v.findViewById(R.id.taste);
                tvToping = (TextView)v.findViewById(R.id.topping);
                tvPrice = (TextView)v.findViewById(R.id.price);
                tvComment = (TextView)v.findViewById(R.id.comment);

                tvTaste.setText(review.taste);
                tvToping.setText(review.topping);
                tvPrice.setText(review.price);
                tvComment.setText(review.comment);
            }

            return v;
        }
    }
}

