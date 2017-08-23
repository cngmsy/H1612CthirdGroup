package com.jiyun.qcloud.dashixummoban.ui.more;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.MoreTVAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.MorelvAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.more.Geng;
import com.jiyun.qcloud.dashixummoban.ui.more.more.MoreMv;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.hintview.ColorPointHintView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MorePageFragment extends BaseFragment implements MoreMv.View {

    @BindView(R.id.lv_more_tv)
    ListView lvMoreTv;
    Unbinder unbinder;

    private MoreMv.Presenter presenter;

    private List<Geng.DataBean.TrailersBean> gengs = new ArrayList<>();
    private List<String> list = new ArrayList<>();
    private MoreTVAdapter moreTVAdapter;
    private MorelvAdapter adapter;

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
        unbinder = ButterKnife.bind(this, view);
        View view1 = LayoutInflater.from(getActivity()).inflate(R.layout.item_more_rollpv, null);
        RollPagerView rollPagerView = (RollPagerView) view1.findViewById(R.id.item_head_rollpagerview);
        rollPagerView.setPlayDelay(2000);
        //设置透明度
        rollPagerView.setAnimationDurtion(500);
        rollPagerView.setHintView(new ColorPointHintView(getActivity(), Color.YELLOW, Color.WHITE));
        moreTVAdapter = new MoreTVAdapter(getActivity(),list);
        rollPagerView.setAdapter(moreTVAdapter);
        lvMoreTv.addHeaderView(view1);
        adapter = new MorelvAdapter(getActivity(),gengs);
        lvMoreTv.setAdapter(adapter);
    }

    @Override
    public void showHomeListData(Geng geng) {
        List<Geng.DataBean.TrailersBean> trailers = geng.getData().getTrailers();
        gengs.addAll(trailers);
        for (int i = 0; i < trailers.size(); i++) {
            Geng.DataBean.TrailersBean trailersBean = trailers.get(i);
            String coverImg = trailersBean.getCoverImg();
            list.add(coverImg);
        }
        moreTVAdapter.notifyDataSetChanged();
        adapter.notifyDataSetChanged();
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
}
