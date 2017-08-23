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
import com.jiyun.qcloud.dashixummoban.entity.HomeBeans;

import java.util.List;

/**
 * Created by lenovo on 2017/8/21.
 */

public class GrildViewAdapter extends BaseAdapter {
    private Context context;
    private List<HomeBeans.HeadBean.CategorieListBean> list;

    public GrildViewAdapter(Context context, List<HomeBeans.HeadBean.CategorieListBean> list) {
        this.context = context;
        this.list = list;
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
        GrildHodler hodler;
        if (view == null) {
            hodler = new GrildHodler();
            view = LayoutInflater.from(context).inflate(R.layout.item_grildview, null);
            hodler.img = view.findViewById(R.id.image);
            hodler.tv = view.findViewById(R.id.text);
            view.setTag(hodler);
        } else {
            hodler = (GrildHodler) view.getTag();
        }
        Glide.with(context).load(list.get(i).getPic()).into(hodler.img);
        hodler.tv.setText(list.get(i).getName());
        return view;
    }

    static class GrildHodler {
        private ImageView img;
        private TextView tv;
    }
}
