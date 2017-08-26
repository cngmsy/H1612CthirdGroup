package com.jiyun.qcloud.dashixummoban.ui.order;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.OrderAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.BodyBean;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.Infomaction;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.MyInfo;
import com.jiyun.qcloud.dashixummoban.ui.order.dingdan.DingDan;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

public class OrderPageFragment extends BaseFragment  implements DingDan.View {
	
	
	
	
	
	
    @BindView(R.id.lv_order)
    ListView lvOrder;
    Unbinder unbinder;
    DingDan.Presenter presenter;
    List<BodyBean> list = new ArrayList<>();
    private OrderAdapter adapter;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_live;
    }

    @Override
    protected void initData() {
        if (presenter != null) {
            presenter.start();
        }
        initAdapter();
    }

    private void initAdapter() {
        adapter = new OrderAdapter(getActivity(),list);
        lvOrder.setAdapter(adapter);
    }

    @Override
    public void showHomeListData(Infomaction dingDanBean) {
        Gson gson = new Gson();
        String data = dingDanBean.getData();
        Type type1 = new TypeToken<MyInfo>() {
        }.getType();
        MyInfo info = gson.fromJson(data, type1);
        List<BodyBean> body = info.getBody();
        list.addAll(body);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void initView(View view) {
        unbinder = ButterKnife.bind(this, view);
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
    public void setPresenter(DingDan.Presenter presenter) {
        this.presenter = presenter;
    }

}
