package com.example.teacher.technoodleandroid;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.example.teacher.technoodleandroid.adapter.RamenItemAdapter;
import com.example.teacher.technoodleandroid.entity.RamenItem;
import com.example.teacher.technoodleandroid.util.GeocoderManager;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends ActionBarActivity {

    // フィールドで変数設定
    private ListView mListRamen;
    private SwipeRefreshLayout mSwipeListRamen;



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

                        List<RamenItem> addList = new ArrayList<>();
                        addList.add(new RamenItem("takeda", null));
                        // 更新内容
                        addDataAdapter(adapter, addList);

                        // ListviewのAdapterへ設定
                        mListRamen.setAdapter(adapter);

                        if (mSwipeListRamen.isRefreshing())
                            mSwipeListRamen.setRefreshing(false);
                    }
                });

    }

    // リストデータ生成
   final List<RamenItem> RamenLst = new ArrayList<>();



    public RamenItemAdapter dataLoadAdapter(RamenItemAdapter adapter) {


        // リストデータ組立
        //ここの時点でAPIからラーメン情報を取得

        GeocoderManager geocode_manager = new GeocoderManager(this);

         String tmp = geocode_manager.getAddressFromGeocode(43.06311, 141.353);

        //RamenItemListにデータを追加

        // リストデータをAdapterへ設定
        RamenLst.add(new RamenItem("kato", null));
        RamenLst.add(new RamenItem("sato", null));
        RamenLst.add(new RamenItem(tmp, null));



        adapter.addLst(RamenLst);

        return adapter;
    }

    public RamenItemAdapter addDataAdapter(RamenItemAdapter adapter ,List<RamenItem> list){

        adapter.addLst(list);

        return adapter;
    }

}
