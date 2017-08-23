package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.HomeBeans;

import java.util.List;

/**
 * Created by lenovo on 2017/8/21.
 */

public class HomeAdapter extends BaseAdapter {
    private List<HomeBeans.BodyBean.SellerBean> list;
    private List<HomeBeans.BodyBean> bodyBeen;
    private List<String> mList;
    private Context context;
    private static final int ONE = 0;
    private static final int TWO = 1;

    public HomeAdapter(Context context, List<HomeBeans.BodyBean.SellerBean> list, List<HomeBeans.BodyBean> bodyBeen, List<String> mList) {
        this.list = list;
        this.context = context;
        this.bodyBeen = bodyBeen;
        this.mList = mList;
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
        switch (bodyBeen.get(i).getType()) {
            case ONE:
                MHolder mHolder;
                if (view == null) {
                    mHolder = new MHolder();
                    view = LayoutInflater.from(context).inflate(R.layout.item_home_data, null);
                    mHolder.tv = view.findViewById(R.id.item_home_tv);
                    view.setTag(mHolder);
                } else {
                    mHolder = (MHolder) view.getTag();
                }
                mHolder.tv.setText(list.get(i).getName());
                break;
            case TWO:
                CHoler cHoler;
                if (view == null) {
                    cHoler = new CHoler();
                    view = LayoutInflater.from(context).inflate(R.layout.item_home_img, null);
                    cHoler.tv1 = view.findViewById(R.id.tv1);
                    cHoler.tv2 = view.findViewById(R.id.tv2);
                    cHoler.tv3 = view.findViewById(R.id.tv3);
                    cHoler.tv4 = view.findViewById(R.id.tv4);
                    cHoler.tv5 = view.findViewById(R.id.tv5);
                    cHoler.tv6 = view.findViewById(R.id.tv6);
                    view.setTag(cHoler);
                } else {
                    cHoler = (CHoler) view.getTag();
                }
                cHoler.tv1.setText(mList.get(0));
                cHoler.tv2.setText(mList.get(1));
                cHoler.tv3.setText(mList.get(2));
                cHoler.tv4.setText(mList.get(3));
                cHoler.tv5.setText(mList.get(4));
                cHoler.tv6.setText(mList.get(5));
                break;
        }
        return view;
    }


    @Override
    public int getItemViewType(int position) {
        if (bodyBeen.get(position).getType() == 0) {
            return ONE;
        } else if (bodyBeen.get(position).getType() == 1) {
            return TWO;
        }
        return position;
    }
    static class MHolder {
        private TextView tv;
    }

    static class CHoler {
        private TextView tv1, tv2, tv3, tv4, tv5, tv6;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }
}
