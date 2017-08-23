package com.jiyun.qcloud.dashixummoban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JieActivity extends BaseActivity {


    @BindView(R.id.tv_count_price)
    TextView tvCountPrice;
    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @Override
    protected void initData() {
        Intent intent = getIntent();
        String qian = intent.getStringExtra("qian");
        tvCountPrice.setText("待支付￥"+qian);
        tvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_jie;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
