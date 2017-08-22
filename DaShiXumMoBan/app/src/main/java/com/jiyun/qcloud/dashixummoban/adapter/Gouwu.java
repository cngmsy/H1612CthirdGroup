package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.modle.db.Gou;

import java.util.List;

/**
 * Created by Administrator on 2017/8/15 0015.
 */

public class Gouwu extends BaseAdapter {

    private final Context context;
    private final List<Gou> list;

    public Gouwu(Context context, List<Gou> gous) {
        this.context = context;
        this.list = gous;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Vied vied;
        if (view == null) {
            vied = new Vied();
            view = LayoutInflater.from(context).inflate(R.layout.item_cart, null);
            vied.item_iv = (ImageView) view.findViewById(R.id.item_iv);
            vied.item_tv_name = (TextView) view.findViewById(R.id.item_tv_name);
            vied.item_tv_price = (TextView) view.findViewById(R.id.item_tv_price);
            vied.item_tv_num = (TextView) view.findViewById(R.id.item_tv_num);
            view.setTag(vied);
        }else {
            vied= (Vied) view.getTag();
        }

            Glide.with(context).load(list.get(i).getImage()).into(vied.item_iv);
            vied.item_tv_name.setText(list.get(i).getName());
            vied.item_tv_price.setText("￥"+list.get(i).getJia());
            vied.item_tv_num.setText(list.get(i).getPosition()+"");

        return view;
    }

    class Vied {
        public View rootView;
        public ImageView item_iv;
        public TextView item_tv_name;
        public TextView item_tv_price;
        public TextView item_tv_num;
    }

}
