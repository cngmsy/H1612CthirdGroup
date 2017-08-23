package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.Array;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/8/13 0013.
 */

public class Homo2Adapter extends BaseAdapter {
    private final Context context;
    private final ArrayList<Array> list;
    setOnClickListener clickListener;

    public void setText2Listener(setText2Listener text2Listener) {

        this.text2Listener = text2Listener;
    }

    setText2Listener text2Listener;
    public void setSetText1Listener(Homo2Adapter.setText1Listener setText1Listener) {
        this.setText1Listener = setText1Listener;
    }

    setText1Listener setText1Listener;
    public void setClickener(setClickListener clickener) {
        this.clickener = clickener;
    }

    setClickListener clickener;

    public void setClickListener(setOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public Homo2Adapter(Context context, ArrayList<Array> arrayList) {
        this.context = context;
        this.list = arrayList;
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
    public View getView(final int i, View view, final ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.item_goods, null);
        final ViewHolder viewHolder = new ViewHolder(view);
        Glide.with(context).load(list.get(i).getIcon()).into(viewHolder.iv_icon);
        viewHolder.tv_name.setText(list.get(i).getName());
        viewHolder.tv_zucheng.setText(list.get(i).getForm());
        viewHolder.tv_yueshaoshounum.setText("月销售" + list.get(i).getMonthSaleNum() + "份");
        viewHolder.tv_newprice.setText("￥" + (int) list.get(i).getNewPrice());
        viewHolder.tv_title.setText(list.get(i).getAname());
        String num = String.valueOf(list.get(i).getA());
        if(num.equals("0")){
            viewHolder.ib_minus.setVisibility(View.GONE);
            viewHolder.tv_money.setVisibility(View.GONE);
        }else {
            viewHolder.ib_minus.setVisibility(View.VISIBLE);
            viewHolder.tv_money.setVisibility(View.VISIBLE);
        }
        viewHolder.tv_money.setText(list.get(i).getA()+"");
        if (i == 0) {
            viewHolder.tv_title.setVisibility(View.VISIBLE);
        } else if (list.get(i).getAname().equals(list.get(i-1).getAname())) {
            viewHolder.tv_title.setVisibility(View.GONE);
        }
        viewHolder.ib_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(view,i);

            }
        });
    viewHolder.ib_minus.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            clickener.onClick(view,i);

        }
    });
        return view;
    }
    public interface setText1Listener{
        void onClick(View view, int i);
    }
    public interface setText2Listener{
        void onClick(View view, int i);
    }
    public interface setClickListener{
        void onClick(View view, int i);
    }
public interface setOnClickListener{
    void onClick(View view, int i);
}
    public static class ViewHolder {
        public TextView tv_title;
        public View rootView;
        public ImageView iv_icon;
        public TextView tv_name;
        public TextView tv_zucheng;
        public TextView tv_yueshaoshounum;
        public TextView tv_newprice;
        public TextView tv_oldprice;
        public ImageButton ib_minus;
        public TextView tv_money;
        public ImageButton ib_add;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.tv_title = (TextView) rootView.findViewById(R.id.item_home_title);
            this.iv_icon = (ImageView) rootView.findViewById(R.id.iv_icon);
            this.tv_name = (TextView) rootView.findViewById(R.id.tv_name);
            this.tv_zucheng = (TextView) rootView.findViewById(R.id.tv_zucheng);
            this.tv_yueshaoshounum = (TextView) rootView.findViewById(R.id.tv_yueshaoshounum);
            this.tv_newprice = (TextView) rootView.findViewById(R.id.tv_newprice);
            this.tv_oldprice = (TextView) rootView.findViewById(R.id.tv_oldprice);
            this.ib_minus = (ImageButton) rootView.findViewById(R.id.ib_minus);
            this.tv_money = (TextView) rootView.findViewById(R.id.tv_money);
            this.ib_add = (ImageButton) rootView.findViewById(R.id.ib_add);
        }

    }


}
