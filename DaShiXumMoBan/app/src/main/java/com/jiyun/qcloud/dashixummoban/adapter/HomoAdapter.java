package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Homm2Ben;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class HomoAdapter extends BaseAdapter {
    private final ArrayList<Homm2Ben> list;
    private final Context context;
    private int selectItem = 0;
    public HomoAdapter(Context context, ArrayList<Homm2Ben> list) {
        this.context=context;
        this.list=list;
    }
    public int getSelectItem() {
        return selectItem;
    }

    public void setSelectItem(int selectItem) {
        this.selectItem = selectItem;
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
        Viewhol viewhol;
        if (view==null) {
            viewhol=new Viewhol();
            view = LayoutInflater.from(context).inflate(R.layout.hom_item, null);
            viewhol.view = view.findViewById(R.id.homo_text);
            view.setTag(viewhol);
        }else {
            viewhol= (Viewhol) view.getTag();
        }
        if(i == selectItem){
            viewhol.view.setBackgroundColor(Color.WHITE);
            //viewhol.view.setTextColor(context.getResources().getColor(R.color.text_green));
        }else {
            viewhol.view.setBackgroundColor(context.getResources().getColor(R.color.hiu));
           // viewhol.view.setTextColor(context.getResources().getColor(R.color.text_deep));
        }
        viewhol.view.setText(list.get(i).getName());
        return view;
    }
    class Viewhol{
        TextView view;
    }
}
