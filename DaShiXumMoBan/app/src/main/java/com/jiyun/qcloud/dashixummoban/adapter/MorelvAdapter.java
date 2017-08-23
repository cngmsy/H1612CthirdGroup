package com.jiyun.qcloud.dashixummoban.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.entity.more.Geng;

import java.util.List;
/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class MorelvAdapter extends BaseAdapter {
    private Context context;
    private List<Geng.DataBean.TrailersBean> gengs;

    public MorelvAdapter(Context context, List<Geng.DataBean.TrailersBean> gengs) {
        this.context = context;
        this.gengs = gengs;
    }

    @Override
    public int getCount() {
        return gengs.size();
    }

    @Override
    public Object getItem(int i) {
        return gengs.get(i);
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
            view = LayoutInflater.from(context).inflate(R.layout.item_lv, null);
            holder.videoPlayer = view.findViewById(R.id.surface_view);
            holder.title = view.findViewById(R.id.item_movetitle);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        Geng.DataBean.TrailersBean trailersBean = gengs.get(i);
        holder.title.setText(trailersBean.getMovieName());
         //设置控制器，该controller可以自定义
        holder.videoPlayer.setMediaController(new MediaController(context));
        //获取焦点
        holder.videoPlayer.requestFocus();
        Uri uri = Uri.parse(trailersBean.getUrl());
        String s = uri.toString();
        holder.videoPlayer.setVideoPath(s);
        return view;
    }

    class ViewHolder {
        TextView title;
        VideoView videoPlayer;
    }
}
