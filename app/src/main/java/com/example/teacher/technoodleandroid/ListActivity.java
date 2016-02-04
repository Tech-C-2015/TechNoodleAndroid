package com.example.teacher.technoodleandroid;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.example.teacher.technoodleandroid.adapter.RamenItemAdapter;
import com.example.teacher.technoodleandroid.client.ServerApiCall;
import com.example.teacher.technoodleandroid.entity.CreateParams;
import com.example.teacher.technoodleandroid.entity.RamenItem;

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

        CreateParams mParams = new CreateParams();
        mParams.CreateRamenItemParam();
        mParams.setRegion("新宿区");

        new ServerApiCall().callStoreList(new ServerApiCall.Listener<List<RamenItem>>() {
            @Override
            public void onFinish(List<RamenItem> obj) {

                if (obj == null) return;

                //String id = obj.get(0).getId();
                adapter.addLst(obj);

            }
        }, (AppController) ListActivity.this.getApplication(), mParams.getParams());


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
                    }
                });



    }

    // リストデータ生成
   final List<RamenItem> RamenLst = new ArrayList<>();


    //Ramenデータのローダー
    public RamenItemAdapter dataLoadAdapter(final RamenItemAdapter adapter) {



        // リストデータ組立
        //ここの時点でAPIからラーメン情報を取得

     //   GeocoderManager geocode_manager = new GeocoderManager(this);

      //  String tmp = geocode_manager.getAddressFromGeocode(43.06311, 141.353);

        //RamenItemListにデータを追加

        // リストデータをAdapterへ設定
        RamenItem ite = new RamenItem();
        ite.set_name("kato");
       RamenLst.add(ite);
//        RamenLst.add(new RamenItem(1,"sato", null, null, null, null, null));
//        RamenLst.add(new RamenItem(2,tmp, null, null, null, null, null));



        adapter.addLst(RamenLst);

        return adapter;

    }

    //ラーメンデータの追加
    public RamenItemAdapter addDataAdapter(RamenItemAdapter adapter ,List<RamenItem> list){

        adapter.addLst(list);

        return adapter;
    }

}
