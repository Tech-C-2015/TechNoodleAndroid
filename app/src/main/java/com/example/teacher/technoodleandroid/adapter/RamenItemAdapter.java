package com.example.teacher.technoodleandroid.adapter;

/**
 * Created by student on 2016/01/30.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.teacher.technoodleandroid.R;
import com.example.teacher.technoodleandroid.entity.RamenItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ListView用のAdapterクラス　※レイアウトを操作したい場合に用意する
 * ラーメン店リスト表示画面用のadapter
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
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
            holder.rImage = (ImageView) convertView.findViewById(R.id.item_image);
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

       holder.txtName.setText(item.get_name());
       holder.rImage.setImageBitmap(item.get_ramenBitmap());
/*
        final ImageView image_view = holder.rImage;
        AsyncTask<String, Void, Bitmap> async = new AsyncTask<String, Void, Bitmap>(){


            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);

                if(bitmap != null)
                image_view.setImageBitmap(bitmap);

            }

            @Override
            protected Bitmap doInBackground(String... strings) {
                Bitmap image;
                try {
                    URL imageUrl = new URL(strings[0]);
                    InputStream imageIs;
                    imageIs = imageUrl.openStream();
                    image = BitmapFactory.decodeStream(imageIs);

                }catch(MalformedURLException e){
                    image = null;
                }catch(IOException e){
                    image = null;
                }
                return image;
            }
        }.execute(item.get_ramenImage());

*/

       // holder.rImage.setImageBitmap(image);
        // holder.rImage.setImageDrawable(item.getRamenImage().getDrawable());

        return convertView;
    }

    private class Holder {
        ImageView rImage;
        TextView txtName;

    }
}