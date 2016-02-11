package com.example.teacher.technoodleandroid;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.teacher.technoodleandroid.adapter.RamenItemAdapter;
import com.example.teacher.technoodleandroid.client.ServerApiCall;
import com.example.teacher.technoodleandroid.entity.CreateParams;
import com.example.teacher.technoodleandroid.entity.RamenItem;
import com.example.teacher.technoodleandroid.util.GeocoderManager;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends ActionBarActivity {

    // フィールドで変数設定
    private ListView mListRamen;
    private SwipeRefreshLayout mSwipeListRamen;
    final static List<RamenItem> RamenLst = new ArrayList<>();

    //ラーメン店情報の取得のためのURL
    private static final String RamenItemURL = "http://133.130.106.164/Tech-Noodle-Api/public/noodle/list";
    private static final String RamenReviewURL = "http://133.130.106.164/Tech-Noodle-Api/public/review/review";

    final CreateParams mParams = new CreateParams();
    final String[] gps_prefecture = new String[5];
    final String[] gps_region = new String[5];
    final String[] gps_address = new String[5];


    //リフレッシュして追加するときのオフセットのカウント用変数
    private Integer how_many_refreshies = 0;
    //一度に取得するitemの個数
    private static Integer item_limit = 10;
    private RelativeLayout list_paranet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // レイアウトインスタンス生成
        mListRamen = (ListView) findViewById(R.id.listRamen);
        mSwipeListRamen = (SwipeRefreshLayout) findViewById(R.id.swipeListRamen);
        mParams.CreateRamenItemParam();
        final GeocoderManager geocode_manager = new GeocoderManager(this);
        list_paranet = (RelativeLayout) findViewById(R.id.parentListview);

        final RamenItemAdapter adapter = new RamenItemAdapter(this);


        dataLoadAdapter(adapter);

        // ListviewのAdapterへ設定
        mListRamen.setAdapter(adapter);


        //Listviewのクリックイベント
        mListRamen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // リストからクリックされたitemを取得
                RamenItem item = (RamenItem) adapter.getItem(position);
                // RamenItem item = RamenLst.get(position);

                // 画面遷移
                Intent intent =
                        new Intent(ListActivity.this,
                                CommentActivity.class);
                //Serializableインターフェイスをimplementsしたクラス
                //intentからデータを取得する場合は
                //RamenItem item = (RamenItem)getIntent().getSerializableExtra("item");
                //と記述する必要がある
                intent.putExtra("item", item);
                startActivity(intent);

            }
        });


        // Listviewをpullして追加するデータイベント
        mSwipeListRamen.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        how_many_refreshies++;
                        Integer offset = how_many_refreshies * item_limit;
                        mParams.setOffset(offset.toString());

                        //サーバーと通信してラーメン店情報を取得
                        new ServerApiCall().callStoreList(new ServerApiCall.Listener<List<RamenItem>>() {
                            @Override
                            public void onFinish(List<RamenItem> obj) {

                                if (obj == null) return;
/*
                                List<RamenItem> items = new ArrayList<RamenItem>();
                                for (RamenItem ramen : obj) {

                                    if (ramen.get_address().indexOf(gps_address[GeocoderManager.ORIGINAL]) != -1
                                            || ramen.get_address().indexOf(gps_address[GeocoderManager.NORTH]) != -1
                                            || ramen.get_address().indexOf(gps_address[GeocoderManager.SOUTH]) != -1
                                            || ramen.get_address().indexOf(gps_address[GeocoderManager.WEST]) != -1
                                            || ramen.get_address().indexOf(gps_address[GeocoderManager.EAST]) != -1)

                                        items.add(ramen);
                                }
*/
                                new AsyncTask<List<RamenItem>, Void, List<RamenItem>>() {

                                    @Override
                                    protected List<RamenItem> doInBackground(List<RamenItem>... ramens) {

                                        //RamenItemオブジェクトに格納されているURLからBitmapイメージをダウンロードしてオブジェクトにセット
                                        for (RamenItem ramen : ramens[0]) {
                                            ramen.set_ramenBitmap();
                                        }

                                        return ramens[0];
                                    }
                                }.execute(obj);

                                adapter.addLst(obj);

                            }
                        }, (AppController) ListActivity.this.getApplication(), mParams.getParams());

                        // ListviewのAdapterへ設定
                        mListRamen.setAdapter(adapter);


                        if (mSwipeListRamen.isRefreshing()) {
                            mSwipeListRamen.setRefreshing(false);


                        }

                        list_paranet.invalidate();
                        return;
                    }
                });


    }

    // リストデータ生成


    //アドレスのの
    final static int FULL_ADDRESS = 0;
    final static int PREFECTURE = 1;
    final static int SHICYOSON = 2;
    final static int GUN = 3;
    final static int TYOME = 4;
    final static int BANCHI = 5;
    final static int GOU = 6;

    //一度あたりの数値（300M）
    final static double gps_to_metar = 0.000277778;
    final static double latitude_const = 30.8184;
    final static double longitude_const = 25.2450;
    final static int gps_area_as_metar = 300;

    //Ramenデータのローダー
    public RamenItemAdapter dataLoadAdapter(final RamenItemAdapter adapter) {

        // リストデータ組立
        //ここの時点でAPIからラーメン情報を取得

        final GeocoderManager geocode_manager = new GeocoderManager(this);

        double latitude;
        double longitude;

        latitude = 43.06311;
        longitude = 141.353;
        String[][] area_gps;
        try {
            area_gps = geocode_manager.getAreaAddressFromGeocode(latitude, longitude, gps_area_as_metar);


            for (int i = 0; i < 5; i++) {
                gps_prefecture[i] = area_gps[i][PREFECTURE];
                gps_region[i] = area_gps[i][SHICYOSON];


                StringBuilder strbuild = new StringBuilder();
                if (area_gps[GeocoderManager.ORIGINAL][GUN] != null) {
                    strbuild.append(area_gps[GeocoderManager.ORIGINAL][GUN]);
                }
                strbuild.append(area_gps[GeocoderManager.ORIGINAL][TYOME]);
                // strbuild.append(area_gps[geocode_manager.ORIGINAL][BANCHI]);
                // strbuild.append(area_gps[geocode_manager.ORIGINAL][GOU]);
                gps_address[i] = strbuild.toString();
            }
            Toast.makeText(this,gps_address[0], Toast.LENGTH_SHORT).show();
            mParams.setRegion(gps_region[GeocoderManager.ORIGINAL]);
            mParams.setLimit(item_limit.toString());

        } catch (Exception e) {
            Log.e("gecodeError", e.getMessage());
            area_gps = null;
        }


        new ServerApiCall().callStoreList(new ServerApiCall.Listener<List<RamenItem>>() {
            @Override
            public void onFinish(List<RamenItem> obj) {

                List<RamenItem> items = new ArrayList<RamenItem>();
                if (obj == null) return;

/*
                for (RamenItem ramen : obj) {

                    if (ramen.get_address().indexOf(gps_address[geocode_manager.ORIGINAL]) != -1
                            || ramen.get_address().indexOf(gps_address[geocode_manager.NORTH]) != -1
                            || ramen.get_address().indexOf(gps_address[geocode_manager.SOUTH]) != -1
                            || ramen.get_address().indexOf(gps_address[geocode_manager.WEST]) != -1
                            || ramen.get_address().indexOf(gps_address[geocode_manager.EAST]) != -1)

                        items.add(ramen);
                }
*/
                new AsyncTask<List<RamenItem>, Void, List<RamenItem>>() {

                    @Override
                    protected List<RamenItem> doInBackground(List<RamenItem>... ramens) {


                        for (RamenItem ramen : ramens[0]) {
                            ramen.set_ramenBitmap();
                        }

                        return ramens[0];
                    }

                }.execute(obj);

                list_paranet.invalidate();

                adapter.addLst(obj);

            }
        }, (AppController) ListActivity.this.getApplication(), mParams.getParams());

        return adapter;

    }

    //ラーメンデータの追加
    public RamenItemAdapter addDataAdapter(RamenItemAdapter adapter, List<RamenItem> list) {

        adapter.addLst(list);

        return adapter;
    }

    public void onStart() {
        super.onStart();
        list_paranet.invalidate();

    }


}
