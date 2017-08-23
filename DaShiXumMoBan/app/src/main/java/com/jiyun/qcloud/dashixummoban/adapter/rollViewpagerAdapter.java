package com.jiyun.qcloud.dashixummoban.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.entity.HomeBeans;
import com.jude.rollviewpager.adapter.StaticPagerAdapter;

import java.util.List;

/**
 * Created by lenovo on 2017/8/18.
 */

public class rollViewpagerAdapter extends StaticPagerAdapter {
    private List<HomeBeans.HeadBean.PromotionListBean> listBeen;

    public rollViewpagerAdapter(List<HomeBeans.HeadBean.PromotionListBean> listBeen) {
        this.listBeen = listBeen;
    }

    @Override
    public View getView(ViewGroup container, int position) {
        ImageView imageView = new ImageView(container.getContext());
        Glide.with(container.getContext()).load(listBeen.get(position).getPic()).into(imageView);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        return imageView;
    }

    @Override
    public int getCount() {
        return listBeen.size();
    }
}
