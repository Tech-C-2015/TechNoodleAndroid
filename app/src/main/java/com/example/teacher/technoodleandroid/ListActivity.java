package com.example.teacher.technoodleandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

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

    private static final String RamenItemURL = "http://133.130.106.164/Tech-Noodle-Api/public/noodle/list";

    private static final String RamenReviewURL = "http://133.130.106.164/Tech-Noodle-Api/public/review/review";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // レイアウトインスタンス生成
        mListRamen = (ListView) findViewById(R.id.listRamen);
        mSwipeListRamen = (SwipeRefreshLayout) findViewById(R.id.swipeListRamen);

        final RamenItemAdapter adapter  = new RamenItemAdapter(this);



         dataLoadAdapter(adapter);
        // ListviewのAdapterへ設定
        mListRamen.setAdapter(adapter);




        /*
        // Listviewのクリックイベント
        mListRamen.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // リストからクリックされたitemを取得
                RamenItem item = RamenLst.get(position);

                // 画面遷移
                Intent intent =
                        new Intent(ListActivity.this,
                                item.getActivityClass());
                startActivity(intent);
            }
        });
        */

        // Listviewをpullして追加するデータイベント
        mSwipeListRamen.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {

/*
                        // 更新内容
                        addDataAdapter(adapter, null);

                        // ListviewのAdapterへ設定
                        mListRamen.setAdapter(adapter);

                        if (mSwipeListRamen.isRefreshing())
                            mSwipeListRamen.setRefreshing(false);
                            */
                        return;
                    }
                });



    }

    // リストデータ生成
   final List<RamenItem> RamenLst = new ArrayList<>();

    final static int FULL_ADDRESS = 0;
    final static int PREFECTURE = 1;
    final static int SHICYOSON = 2;
    final static int GUN = 3;
    final static int TYOME = 4;
    final static int BANCHI = 5;
    final static int GOU = 6;

    //一度あたりの数値（300M）
    final static double gps_to_metar = 0.00277778;
    //Ramenデータのローダー
    public RamenItemAdapter dataLoadAdapter(final RamenItemAdapter adapter) {

        // リストデータ組立
        //ここの時点でAPIからラーメン情報を取得

       GeocoderManager geocode_manager = new GeocoderManager(this);
        double latitude;
        double longitude;

        latitude = 43.06311;
        longitude =141.353;


       String[] tmp = geocode_manager.getAddressFromGeocode(43.06311, 141.353);
      // String[] tmp1 = geocode_manager.getAddressFromGeocode();

       String gps_prefecture = tmp[PREFECTURE];
       String gps_region = tmp[SHICYOSON];
       String gps_address;

        StringBuilder strbuild = new StringBuilder();
        if(tmp[GUN] != null)
           strbuild.append(tmp[GUN]);
        strbuild.append(tmp[TYOME]);
        strbuild.append(tmp[BANCHI]);
        strbuild.append(tmp[GOU]);
        gps_address = strbuild.toString();





        //RamenItemListにデータを追加

        // リストデータをAdapterへ設定
       RamenItem ite = new RamenItem();
       ite.set_name(tmp[0]);
      // RamenLst.add(ite);
//        RamenLst.add(new RamenItem(1,"sato", null, null, null, null, null));
      //RamenLst.add(new RamenItem(2,tmp, null, null, null, null, null));


        CreateParams mParams = new CreateParams();
        mParams.CreateRamenItemParam();
        mParams.setRegion("新宿区");


        new ServerApiCall().callStoreList(new ServerApiCall.Listener<List<RamenItem>>() {
            @Override
            public void onFinish(List<RamenItem> obj) {

                if (obj == null) return;

                for(RamenItem ramen: obj) {
                    RamenLst.add(ramen);
                }
                //String id = obj.get(0).getId();
                for(RamenItem ramen: obj){

                    AsyncTask<List<RamenItem>, Void, List<RamenItem>> async = new AsyncTask<List<RamenItem>, Void, List<RamenItem>>(){

                        @Override
                        protected List<RamenItem> doInBackground(List<RamenItem>... ramens) {


                            for(RamenItem ramen: ramens[0]){
                                ramen.set_ramenBitmap();
                            }

                            return ramens[0];
                        }
                    }.execute(obj);
                }
                adapter.addLst(obj);

            }
        }, (AppController) ListActivity.this.getApplication(), mParams.getParams());

        adapter.addLst(RamenLst);

        return adapter;

    }

    //ラーメンデータの追加
    public RamenItemAdapter addDataAdapter(RamenItemAdapter adapter ,List<RamenItem> list){

        adapter.addLst(list);

        return adapter;
    }

}
