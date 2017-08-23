package com.jiyun.qcloud.dashixummoban.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.Hom2Adapter;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.ui.GouPresenter.GouPresenter;
import com.jiyun.qcloud.dashixummoban.ui.GouPresenter.Hom2Fragment;
import com.jiyun.qcloud.dashixummoban.ui.GouPresenter.Homo1Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GouwuActivity extends BaseActivity {

    @BindView(R.id.hom2_text)
    TextView hom2Text;
    @BindView(R.id.tab_hom2)
    TabLayout tabHom2;
    @BindView(R.id.hom2_viewpage)
    ViewPager hom2Viewpage;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        hom2Text.setText(name);
        ArrayList<Fragment> list=new ArrayList<Fragment>();
        Homo1Fragment homo1Fragment = new Homo1Fragment();
        list.add(homo1Fragment);
        list.add(new Hom2Fragment());
        list.add(new Hom2Fragment());

        new GouPresenter(homo1Fragment);
        Hom2Adapter adapter=new Hom2Adapter(getSupportFragmentManager(),list);
        hom2Viewpage.setAdapter(adapter);
        tabHom2.setupWithViewPager(hom2Viewpage);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gouwu;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
