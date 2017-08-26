package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;

import java.util.List;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class MoreTVAdapter extends PagerAdapter {
    private Context context;
    private List<String> gengs;

    public MoreTVAdapter(Context context, List<String> gengs) {
        this.context = context;
        this.gengs = gengs;
    }

    @Override
    public int getCount() {
        return gengs.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_photo, null);
        ImageView photo = (ImageView)  inflate.findViewById(R.id.item_tv_titlephoto);
        Glide.with(context).load(gengs.get(position)).into(photo);
        container.addView(inflate);
        return inflate;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
       container.removeView((View) object);
}
}
