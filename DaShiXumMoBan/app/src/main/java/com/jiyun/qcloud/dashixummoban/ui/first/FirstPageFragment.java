package com.jiyun.qcloud.dashixummoban.ui.first;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.GrildViewAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.HomeAdapter;
import com.jiyun.qcloud.dashixummoban.adapter.rollViewpagerAdapter;
import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;
import com.jiyun.qcloud.dashixummoban.entity.HomeBeans;
import com.jiyun.qcloud.dashixummoban.entity.PandaHome;
import com.jiyun.qcloud.dashixummoban.main.GouwuActivity;
import com.jiyun.qcloud.dashixummoban.main.HostoryActivity;
import com.jiyun.qcloud.dashixummoban.main.MapActivity;
import com.jiyun.qcloud.dashixummoban.utils.ScreenUtils;
import com.jude.rollviewpager.RollPagerView;
import com.zhy.autolayout.AutoLinearLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by chj on 2017/8/20.
 */

public class FirstPageFragment extends BaseFragment implements XRecyclerView.LoadingListener, HomeContract.View {

    @BindView(R.id.recyclerView)
    ListView listView;
    @BindView(R.id.tv_map)
    TextView tvMap;
    @BindView(R.id.imageView)
    ImageView imageView;
    @BindView(R.id.editText)
    EditText editText;
    @BindView(R.id.linear)
    AutoLinearLayout llSearch;
    Unbinder unbinder; //状态栏的高度
    private int statusHeight;
    private HomeContract.Presenter presenter;
    private List<Object> datas;
    private HomeAdapter adapter;
    private GrildViewAdapter grildViewAdapter;
    private GridView gridView;
    private RollPagerView rollPagerView;
    private List<String> mList = new ArrayList<>();
    private List<HomeBeans.HeadBean.PromotionListBean> promotionListBeen;
    private List<HomeBeans.BodyBean.SellerBean> list2;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        presenter.start();
        //on获取状态 set get设置状态
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView absListView, int i) {

            }

            @Override
            public void onScroll(AbsListView absListView, int i, int i1, int i2) {
                int[] ints = new int[2];
                rollPagerView.getLocationOnScreen(ints);
                /**
                 * 轮播图 距离屏幕顶部的距离(图片顶部在屏幕最上面，向上滑动为负数，所以取反)
                 * 如果不隐藏状态栏，需要加上状态栏的高度；隐藏状态栏就不用加了；
                 */
                int scrollY = -ints[1] + statusHeight;

                //mImage这个view的高度
                int imageHeight = rollPagerView.getHeight();

                if (rollPagerView != null && imageHeight > 0) {
                    //如果“图片”没有向上滑动，设置为全透明
                    if (scrollY < 0) {
                        llSearch.getBackground().setAlpha(0);
                    } else {
                        //“图片”已经滑动，而且还没有全部滑出屏幕，根据滑出高度的比例设置透明度的比例
                        if (scrollY < imageHeight) {
                            int progress = (int) (new Float(scrollY) / new Float(imageHeight) * 255);//255
                            llSearch.getBackground().setAlpha(progress);
                        } else {
                            //“图片”全部滑出屏幕的时候，设为完全不透明
                            llSearch.getBackground().setAlpha(255);
                        }
                    }
                }
            }
        });
    }

    @Override
    protected void initView(View view) {
        this.datas = new ArrayList<Object>();
    }


    @Override
    public void setBundle(Bundle bundle) {

    }

    private void setRoll() {
        rollPagerView.setPlayDelay(3000);//*播放间隔
        rollPagerView.setAnimationDurtion(500);//透明度
        rollViewpagerAdapter adapter = new rollViewpagerAdapter(promotionListBeen);
        rollPagerView.setAdapter(adapter);//配置适配器
    }

    @Override
    public void showHomeListData(HomeBean homeBean) {
        String data = homeBean.getData();
        Gson gson = new Gson();
        HomeBeans homeBeans = gson.fromJson(data, HomeBeans.class);
        promotionListBeen = homeBeans.getHead().getPromotionList();
        setRoll();
        List<HomeBeans.HeadBean.CategorieListBean> categorieList = homeBeans.getHead().getCategorieList();
        List<HomeBeans.BodyBean> body = homeBeans.getBody();
        list2 = new ArrayList<HomeBeans.BodyBean.SellerBean>();
        for (int i = 0; i < homeBeans.getBody().size(); i++) {
            list2.add(homeBeans.getBody().get(i).getSeller());
            mList.add(String.valueOf(homeBeans.getBody().get(i).getRecommendInfos()));
        }
        grildViewAdapter = new GrildViewAdapter(getActivity(), categorieList);
        gridView.setAdapter(grildViewAdapter);
        grildViewAdapter.notifyDataSetChanged();
        adapter = new HomeAdapter(getActivity(), list2, body, mList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(getActivity(), GouwuActivity.class);
                intent.putExtra("name", list2.get(i-1).getName());
                startActivity(intent);
            }
        });
    }

    @Override
    public void playVideo() {

    }

    @Override
    public void loadWebView() {

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
    public void setPresenter(HomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void onRefresh() {
        presenter.start();
    }

    @Override
    public void onLoadMore() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        statusHeight = ScreenUtils.getStatusHeight(this);
        View headerView = View.inflate(getActivity(), R.layout.binner, null);
        gridView = headerView.findViewById(R.id.gridview);
        rollPagerView = headerView.findViewById(R.id.rollpagerView);
        listView.addHeaderView(headerView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.editText, R.id.tv_map})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editText:
                Intent intent = new Intent(getActivity(), HostoryActivity.class);
                App.mBaseActivity.startActivity(intent);
                break;
            case R.id.tv_map:
                Intent intent1 = new Intent(getActivity(), MapActivity.class);
                App.mBaseActivity.startActivity(intent1);
                break;
        }
    }
}
