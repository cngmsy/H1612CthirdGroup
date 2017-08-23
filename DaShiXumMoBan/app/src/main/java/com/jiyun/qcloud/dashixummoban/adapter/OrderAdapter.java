package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.BodyBean;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.SellerBean;

import java.util.List;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class OrderAdapter extends BaseAdapter {
    private Context context;
    private List<BodyBean> list;

    public OrderAdapter(Context context, List<BodyBean> list) {
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
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_order,null);
            holder.orderTitleTv = view.findViewById(R.id.item_order_title);
            holder.orderNameTv = view.findViewById(R.id.item_order_name);
            holder.orderPhoneTv = view.findViewById(R.id.item_order_phone);
            holder.orderIconImg = view.findViewById(R.id.item_order_icon);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        BodyBean bodyBean = list.get(i);
        SellerBean seller = bodyBean.getSeller();
        if (i < 9) {
            holder.orderTitleTv.setText(seller.getName());
            holder.orderTitleTv.setText(seller.getName());
        }else{
            String text = (String) holder.orderTitleTv.getText();
            holder.orderTitleTv.setText(text);
        }
        holder.orderNameTv.setText("红烧肉等3件商品");
        holder.orderPhoneTv.setText("13200000000"+i);
        holder.orderIconImg.setImageResource(R.drawable.item_logo);
        return view;
    }
    class ViewHolder {
        private TextView orderTitleTv,orderNameTv,orderPhoneTv;
        private ImageView orderIconImg;
    }

}
