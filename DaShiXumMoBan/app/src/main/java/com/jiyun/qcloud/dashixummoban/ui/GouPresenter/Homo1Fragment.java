package com.jiyun.qcloud.dashixummoban.ui.GouPresenter;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.Homo2Adapter;
import com.jiyun.qcloud.dashixummoban.adapter.HomoAdapter;
import com.jiyun.qcloud.dashixummoban.base.BaseFragment;
import com.jiyun.qcloud.dashixummoban.entity.Array;
import com.jiyun.qcloud.dashixummoban.entity.Hom2Ben;
import com.jiyun.qcloud.dashixummoban.entity.Homm2Ben;
import com.jiyun.qcloud.dashixummoban.modle.db.DaoMaster;
import com.jiyun.qcloud.dashixummoban.modle.db.DaoSession;
import com.jiyun.qcloud.dashixummoban.modle.db.Gou;
import com.jiyun.qcloud.dashixummoban.modle.db.GouDao;
import com.jiyun.qcloud.dashixummoban.ui.activity.GouActivity;
import com.jiyun.qcloud.dashixummoban.widget.BezierTypeEvaluator;
import com.zhy.autolayout.AutoFrameLayout;
import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Administrator on 2017/8/22 0022.
 */

public class Homo1Fragment extends BaseFragment implements GouContract.View {
    @BindView(R.id.list1)
    ListView list1;
    @BindView(R.id.list2)
    ListView list2;
    @BindView(R.id.list2_item)
    TextView list2Item;
    @BindView(R.id.iv_bezier_curve_shopping_cart)
    ImageView ivBezierCurveShoppingCart;
    @BindView(R.id.tv_bezier_curve_shopping_cart_count)
    TextView tvBezierCurveShoppingCartCount;
    @BindView(R.id.fly_bezier_curve_shopping_cart)
    AutoFrameLayout flyBezierCurveShoppingCart;
    @BindView(R.id.recy)
    AutoRelativeLayout recy;
    Unbinder unbinder;
    ArrayList<Homm2Ben> list = new ArrayList<>();
    private GouContract.Presenter presenter;
    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private GouDao gouDao;
    private HomoAdapter adapter;
    private ArrayList<Array> arrayList;
    private Homo2Adapter adapter1;
    private ArrayList<Integer> showTitle;
    private int currentItem;

    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_homo1;
    }

    @Override
    protected void initData() {
        presenter.start();
        preferences = getActivity().getSharedPreferences("nam", MODE_PRIVATE);
        edit = preferences.edit();
        DaoMaster.DevOpenHelper li = new DaoMaster.DevOpenHelper(getActivity(), "gou");
        DaoMaster master=new DaoMaster(li.getReadableDb());
        DaoSession daoSession = master.newSession();
        gouDao = daoSession.getGouDao();
        String he = preferences.getString("hhh", "0");
        if (he!=null){
        if (he.equals("0")){
            tvBezierCurveShoppingCartCount.setVisibility(View.GONE);
            }else {
                tvBezierCurveShoppingCartCount.setVisibility(View.VISIBLE);
                tvBezierCurveShoppingCartCount.setText(he);
            }
        }else {
            tvBezierCurveShoppingCartCount.setVisibility(View.GONE);
        }

    }
    @Override
    protected void initView(View view) {

    }

    @Override
    public void setBundle(Bundle bundle) {

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
    public void setPresenter(GouContract.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void showHomeListData(Hom2Ben hom2Ben) {
        Gson gson=new Gson();
        list.addAll((Collection<? extends Homm2Ben>) gson.fromJson(hom2Ben.getData(), new TypeToken<ArrayList<Homm2Ben>>() {}.getType()));
        arrayList = new ArrayList();
       initv();
    }

    private void initv() {
        if (list.size()>0) {
            adapter = new HomoAdapter(getActivity(), list);
            list1.setAdapter(adapter);
            List<Homm2Ben.ListBean> been = new ArrayList<Homm2Ben.ListBean>();
            for (int a = 0; a < list.size(); a++) {
                List<Homm2Ben.ListBean> list = Homo1Fragment.this.list.get(a).getList();
                been.addAll(list);
            }

            for (int a = 0; a < list.size(); a++) {
                for (int i = 0; i < list.get(a).getList().size(); i++) {
                    arrayList.add(new Array(list.get(a).getName(), list.get(a).getList().get(i).getForm(), list.get(a).getList().get(i).getIcon(), list.get(a).getList().get(i).getId(), list.get(a).getList().get(i).getMonthSaleNum(), list.get(a).getList().get(i).getName(), list.get(a).getList().get(i).getNewPrice(), list.get(a).getList().get(i).getOldPrice()));
                }
            }
            List<Gou> gous1 = gouDao.loadAll();
            if (gous1.size() != 0) {
                for (int j = 0; j < gous1.size(); j++) {
                    for (int a = 0; a < arrayList.size(); a++) {
                        if (gous1.get(j).getId() - 1 == a) {
                            Array array = arrayList.get(a);
                            array.setA(gous1.get(j).getPosition());
                        }
                    }
                }

            }
            adapter1 = new Homo2Adapter(getActivity(), arrayList);
            list2.setAdapter(adapter1);
            showTitle = new ArrayList<Integer>();
            for (int i = 0; i < arrayList.size(); i++) {
                if (i == 0) {
                    showTitle.add(i);
                } else if (!arrayList.get(i).getAname().equals(arrayList.get(i - 1).getAname())) {
                    showTitle.add(i);
                }
            }
            list2Item.setText(arrayList.get(0).getAname());
            list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                        long arg3) {
                    adapter.setSelectItem(arg2);
                    adapter.notifyDataSetInvalidated();
                    list2.setSelection(showTitle.get(arg2));
                    list2Item.setText(arrayList.get(showTitle.get(arg2)).getAname());

                }
            });
            list2.setOnScrollListener(new AbsListView.OnScrollListener() {
                private int scrollState;

                @Override
                public void onScrollStateChanged(AbsListView view, int scrollState) {
                    this.scrollState = scrollState;
                }

                @Override
                public void onScroll(AbsListView view, int firstVisibleItem,
                                     int visibleItemCount, int totalItemCount) {
                        /*if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                            return;
                        }*/

                    int current = showTitle.indexOf(firstVisibleItem);

                    // Log.e("TAG",current+"");
                    if (currentItem != current && current >= 0) {
                        currentItem = current;
                        list2Item.setText(arrayList.get(showTitle.get(current)).getAname());
                        adapter.setSelectItem(currentItem);
                        adapter.notifyDataSetInvalidated();
                    }
                }
            });

            List<Gou> gous = gouDao.loadAll();
            if (gous.size() == 0) {
                for (int j = 0; j < arrayList.size(); j++) {
                    Gou gou = new Gou(null, arrayList.get(j).getIcon(), arrayList.get(j).getName(), (int) arrayList.get(j).getNewPrice() + "", arrayList.get(j).getA(), tvBezierCurveShoppingCartCount.getText().toString());
                    gouDao.insert(gou);
                }
            }
            BeiSaiEr();
            ivBezierCurveShoppingCart.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), GouActivity.class);
                    startActivity(intent);
                }
            });
            adapter1.setClickener(new Homo2Adapter.setClickListener() {
                @Override
                public void onClick(View view, int i) {
                    for (int j = 0; j < arrayList.size(); j++) {
                        if (j == i) {
                            Array bean = arrayList.get(j);
                            int k = bean.getA() - 1;
                            bean.setA(k);

                            String s = tvBezierCurveShoppingCartCount.getText().toString();
                            if (!s.equals("")) {
                                int i1 = Integer.parseInt(s);
                                if (i1 == 1) {
                                    tvBezierCurveShoppingCartCount.setVisibility(View.GONE);
                                }
                                tvBezierCurveShoppingCartCount.setText(i1 - 1 + "");
                            }
                            Gou gou = new Gou((long) i + 1, bean.getIcon(), bean.getName(), (int) bean.getNewPrice() + "", bean.getA(), tvBezierCurveShoppingCartCount.getText().toString());
                            gouDao.update(gou);
                        }

                    }
                    adapter1.notifyDataSetChanged();
                }
            });
        }
    }

    public void BeiSaiEr() {
        adapter1.setClickListener(new Homo2Adapter.setOnClickListener() {
            @Override
            public void onClick(View view,  int a) {
                for (int i = 0; i < arrayList.size(); i++) {
                    if (i == a) {
                        //点击的对应的item的bean数据源的num数据加1
                        //找到点击的item对应的bean对象
                        Array bean = arrayList.get(i);
                        //让bean对象对应的数据加1
                        int k = bean.getA() + 1;
                        //把加1后的数据赋值给我们点击的那个Bean对象
                        bean.setA(k);
                        tvBezierCurveShoppingCartCount.setVisibility(View.VISIBLE);
                        String s = tvBezierCurveShoppingCartCount.getText().toString();
                        if (!s.equals("")) {
                            int i1 = Integer.parseInt(s);
                            tvBezierCurveShoppingCartCount.setText(i1 + 1 + "");
                        }else {
                            tvBezierCurveShoppingCartCount.setText(1 + "");
                        }

                        Gou gou=new Gou((long)a+1,bean.getIcon(),bean.getName(),(int)bean.getNewPrice()+"",bean.getA(),tvBezierCurveShoppingCartCount.getText().toString());
                        gouDao.update(gou);
                    }
                }
                adapter1.notifyDataSetChanged();
                //贝塞尔起始数据点
                int[] startPosition = new int[2];
                //贝塞尔结束数据点
                int[] endPosition = new int[2];
                //控制点
                int[] recyclerPosition = new int[2];

                view.getLocationInWindow(startPosition);
                ivBezierCurveShoppingCart.getLocationInWindow(endPosition);
                list2.getLocationInWindow(recyclerPosition);

                final PointF startF = new PointF();
                final PointF endF = new PointF();
                PointF controllF = new PointF();

                startF.x = startPosition[0];
                startF.y = startPosition[1] - recyclerPosition[1];
                endF.x = endPosition[0];
                endF.y = endPosition[1] - recyclerPosition[1];
                controllF.x = endF.x;
                controllF.y = startF.y;

                final ImageView imageView = new ImageView(getContext());
                recy.addView(imageView);
                imageView.setImageResource(R.mipmap.food_button_add);
                imageView.setVisibility(View.VISIBLE);
                imageView.setX(startF.x);
                imageView.setY(startF.y);

                ValueAnimator valueAnimator = ValueAnimator.ofObject(new BezierTypeEvaluator(controllF), startF, endF);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        PointF pointF = (PointF) animation.getAnimatedValue();
                        imageView.setX(pointF.x);
                        imageView.setY(pointF.y);
                    }
                });

                ObjectAnimator objectAnimatorX = new ObjectAnimator().ofFloat(ivBezierCurveShoppingCart, "scaleX", 0.4f, 0.8f);
                ObjectAnimator objectAnimatorY = new ObjectAnimator().ofFloat(ivBezierCurveShoppingCart, "scaleY", 0.4f, 0.8f);
                objectAnimatorX.setInterpolator(new AccelerateInterpolator());
                objectAnimatorY.setInterpolator(new AccelerateInterpolator());
                AnimatorSet set = new AnimatorSet();
                set.play(objectAnimatorX).with(objectAnimatorY).after(valueAnimator);
                set.setDuration(800);
                set.start();
                valueAnimator.addListener(new Animator.AnimatorListener() {

                    private TextView count;

                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        // 购物车的数量加1
                        // 把移动的图片imageview从父布局里移除
                        recy.removeView(imageView);
                        adapter1.notifyDataSetChanged();
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });

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
    public void onResume() {
        super.onResume();
      initv();

    }

    @Override
    public void onStop() {
        super.onStop();
        list.clear();
        arrayList.clear();
        String s = tvBezierCurveShoppingCartCount.getText().toString();
        edit.putString("hhh",s);
        edit.commit();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
