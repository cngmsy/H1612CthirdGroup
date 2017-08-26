package com.jiyun.qcloud.dashixummoban.ui.more;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.MoreTVAdapter2;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.more.Geng;
import com.jiyun.qcloud.dashixummoban.shi.VideoBean;
import com.jiyun.qcloud.dashixummoban.ui.more.more.MoreMv;
import com.jiyun.qcloud.dashixummoban.widget.MediaHelp;
import com.jiyun.qcloud.dashixummoban.widget.VideoSuperPlayer;
import com.jude.rollviewpager.RollPagerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MorePageFragment extends BaseFragment implements MoreMv.View {

    @BindView(R.id.lv_more_tv)
    ListView lvMoreTv;
    Unbinder unbinder;
    private boolean isPlaying;
    private MoreMv.Presenter presenter;
    private int indexPostion = -1;
    private List<Geng.DataBean.TrailersBean> gengs = new ArrayList<>();
    private List<String> image = new ArrayList<>();
    private ArrayList<VideoBean> mList;
    private MAdapter mAdapter;
    MoreTVAdapter2 tvAdapter;
    int a;
    private List<Geng.DataBean.TrailersBean> trailers;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_more_page;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.start();
        }
    }

    @Override
    protected void initView(View view) {
        setHeader(view);


    }

    private void setHeader(View view) {
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_more_rollpv, null);
        RollPagerView pagerView = view1.findViewById(R.id.item_head_rollpagerview);
        pagerView.setPlayDelay(2000);
        tvAdapter = new MoreTVAdapter2(getActivity(),image);
        pagerView.setAdapter(tvAdapter);
        lvMoreTv.addHeaderView(view1);
    }

    @Override
    public void showHomeListData(Geng geng) {
        trailers = geng.getData().getTrailers();
        mList = new ArrayList<VideoBean>();
        for (int i = 0; i < trailers.size(); i++) {
            mList.add(new VideoBean(trailers.get(i).getHightUrl()));
            Geng.DataBean.TrailersBean bean = trailers.get(i);
            String coverImg = bean.getCoverImg();
            image.add(coverImg);
        }
        mAdapter = new MAdapter(getActivity());
        lvMoreTv.setAdapter(mAdapter);
        tvAdapter.notifyDataSetChanged();
        lvMoreTv.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                if ((indexPostion < lvMoreTv.getFirstVisiblePosition() || indexPostion > lvMoreTv
                        .getLastVisiblePosition()) && isPlaying) {
                    indexPostion = -1;
                    isPlaying = false;
                    mAdapter.notifyDataSetChanged();
                    MediaHelp.release();
                }
            }
        });
    }

    @Override
    public void setBundle(Bundle bundle) {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void dimissProgress() {

    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void setPresenter(MoreMv.Presenter presenter) {
        this.presenter = presenter;
    }
    class MAdapter extends BaseAdapter {
        private Context context;
        LayoutInflater inflater;

        public MAdapter(Context context) {
            this.context = context;
            inflater = LayoutInflater.from(context);
        }

        @Override
        public VideoBean getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public View getView(int position, View v, ViewGroup parent) {
            a=position;
            GameVideoViewHolder holder = null;
            if (v == null) {
                holder = new GameVideoViewHolder();
                v = inflater.inflate(R.layout.list_video_item, parent, false);
                holder.mVideoViewLayout = (VideoSuperPlayer) v.findViewById(R.id.video);
                holder.mPlayBtnView = (ImageView) v.findViewById(R.id.play_btn);
                holder.icon=(ImageView) v.findViewById(R.id.icon);
                holder.info_title=(TextView) v.findViewById(R.id.info_title);
                v.setTag(holder);
            } else {
                holder = (GameVideoViewHolder) v.getTag();
            }
            holder.info_title.setText(trailers.get(position).getMovieName());
            Glide.with(context).load(trailers.get(position).getCoverImg()).into(holder.icon);
            holder.mPlayBtnView.setOnClickListener(new MyOnclick(
                    holder.mPlayBtnView, holder.mVideoViewLayout, position));
            if (indexPostion == position) {
                holder.mVideoViewLayout.setVisibility(View.VISIBLE);
            } else {
                holder.mVideoViewLayout.setVisibility(View.GONE);
                holder.mVideoViewLayout.close();
            }
            return v;
        }

        class MyOnclick implements View.OnClickListener {
            VideoSuperPlayer mSuperVideoPlayer;
            ImageView mPlayBtnView;
            int position;

            public MyOnclick(ImageView mPlayBtnView,
                             VideoSuperPlayer mSuperVideoPlayer, int position) {
                this.position = position;
                this.mSuperVideoPlayer = mSuperVideoPlayer;
                this.mPlayBtnView = mPlayBtnView;
            }

            @Override
            public void onClick(View v) {
                MediaHelp.release();
                indexPostion = position;
                isPlaying = true;
                mSuperVideoPlayer.setVisibility(View.VISIBLE);
                mSuperVideoPlayer.loadAndPlay(MediaHelp.getInstance(), mList
                        .get(position).getUrl(), 0, false);
                mSuperVideoPlayer.setVideoPlayCallback(new MyVideoPlayCallback(
                        mPlayBtnView, mSuperVideoPlayer, mList.get(position)));
                notifyDataSetChanged();
            }
        }

        class MyVideoPlayCallback implements VideoSuperPlayer.VideoPlayCallbackImpl {
            ImageView mPlayBtnView;
            VideoSuperPlayer mSuperVideoPlayer;
            VideoBean info;

            public MyVideoPlayCallback(ImageView mPlayBtnView,
                                       VideoSuperPlayer mSuperVideoPlayer, VideoBean info) {
                this.mPlayBtnView = mPlayBtnView;
                this.info = info;
                this.mSuperVideoPlayer = mSuperVideoPlayer;
            }

            @Override
            public void onCloseVideo() {
                closeVideo();
            }


            @Override
            public void onSwitchPageType() {
                if (((Activity) context).getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
                    Intent intent = new Intent(new Intent(context,
                            FullVideoActivity.class));
                    intent.putExtra("video", info);
                    intent.putExtra("position",
                            mSuperVideoPlayer.getCurrentPosition());
                    ((Activity) context).startActivityForResult(intent, 1);
                }
            }

            @Override
            public void onPlayFinish() {
                closeVideo();
            }

            private void closeVideo() {
                isPlaying = false;
                indexPostion = -1;
                mSuperVideoPlayer.close();
                MediaHelp.release();
                mPlayBtnView.setVisibility(View.VISIBLE);
                mSuperVideoPlayer.setVisibility(View.GONE);
            }

        }

        class GameVideoViewHolder {
            private VideoSuperPlayer mVideoViewLayout;
            private ImageView mPlayBtnView,icon;
            private TextView info_title;
        }

    }

}
