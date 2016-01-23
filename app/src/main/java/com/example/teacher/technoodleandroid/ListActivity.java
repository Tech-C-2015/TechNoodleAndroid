package com.example.teacher.technoodleandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

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
        //RamenItemListにデータを追加

        // リストデータをAdapterへ設定
        RamenLst.add(new RamenItem("kato", null));
        RamenLst.add(new RamenItem("sato", null));



        adapter.addLst(RamenLst);

        return adapter;
    }

    public RamenItemAdapter addDataAdapter(RamenItemAdapter adapter ,List<RamenItem> list){

        adapter.addLst(list);

        return adapter;
    }


    /**
     * Listview表示用のitemリストクラス
     */
    class RamenItem {
        private ImageView _ramenImage;
        private String _name;


        public String getName() {
            return _name;
        }

        public ImageView getRamenImage(){
            return _ramenImage;
        }

        public RamenItem(String name,  ImageView ramenImage) {
            _name = name;
            _ramenImage = ramenImage;

        }
    }

    /**
     * ListView用のAdapterクラス　※レイアウトを操作したい場合に用意する
     */
    public class RamenItemAdapter extends BaseAdapter {

        private LayoutInflater _layoutInflater;
        private Context _context;
        private List<RamenItem> _list;

        /**
         * コンストラクタ
         *
         * @param context
         */
        public RamenItemAdapter(Context context) {
            _context = context;
            _list = new ArrayList<>();
            _layoutInflater = (LayoutInflater)
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        /**
         * リストデータの追加（単）
         *
         * @param data
         */
        public void add(RamenItem data) {
            _list.add(data);
            notifyDataSetChanged();
        }

        /**
         * リストデータの追加（リスト一式）
         *
         * @param dataLst
         */
        public void addLst(List<RamenItem> dataLst) {
            for (RamenItem data : dataLst)
                add(data);
        }

        /**
         * リストの個数を返す
         *
         * @return
         */
        @Override
        public int getCount() {
            return _list.size();
        }

        /**
         * 指定位置のリストを返す
         *
         * @param position
         * @return
         */
        @Override
        public Object getItem(int position) {
            return _list.get(position);
        }

        /**
         * リストのID値を返す
         *
         * @param position
         * @return
         */
        @Override
        public long getItemId(int position) {
            return 0;
        }

        /**
         * 画面の表示情報を返す
         *
         * @param position
         * @param convertView
         * @param parent
         * @return
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Holder holder = null;
            if (convertView == null) {
                // インスタンス取得
                convertView = _layoutInflater.inflate(R.layout.list_row, null);
              //  LinearLayout ll = (LinearLayout) _layoutInflater.inflate(R.layout.list_row, null);

                // ImageView rImage = new ImageView(ListActivity.this);
                //rImage.setImageResource();
                holder = new Holder();
                holder.txtName=(TextView) convertView.findViewById(R.id.item_name);
                // ((ImageView) ll.findViewById(R.id.item_image)).setImageBitmap(((BitmapDrawable) RamenLst.get(position).getRamenImage().getDrawable()).getBitmap());
               // ((ListView) convertView.findViewById(R.id.listRamen)).addView(ll);


                //  holder.txtTitle = convertView.findViewById();
                convertView.setTag(holder);
            } else {
                holder = (Holder) convertView.getTag();
            }

            // データの取得
            RamenItem item = (RamenItem) getItem(position);

            //　データ設定

            holder.txtName.setText(item.getName());
           // holder.rImage.setImageDrawable(item.getRamenImage().getDrawable());

            return convertView;
        }

        private class Holder {
            ImageView rImage;
            TextView txtName;

        }
    }

}
