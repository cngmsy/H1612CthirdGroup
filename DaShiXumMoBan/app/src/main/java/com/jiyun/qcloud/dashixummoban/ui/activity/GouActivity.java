package com.jiyun.qcloud.dashixummoban.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jiyun.qcloud.dashixummoban.R;
import com.jiyun.qcloud.dashixummoban.adapter.Gouwu;
import com.jiyun.qcloud.dashixummoban.base.BaseActivity;
import com.jiyun.qcloud.dashixummoban.modle.db.DaoMaster;
import com.jiyun.qcloud.dashixummoban.modle.db.DaoSession;
import com.jiyun.qcloud.dashixummoban.modle.db.Gou;
import com.jiyun.qcloud.dashixummoban.modle.db.GouDao;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GouActivity extends BaseActivity {
    @BindView(R.id.fan)
    ImageView fan;
    @BindView(R.id.gouwu_list)
    ListView gouwuList;
    @BindView(R.id.qian)
    TextView qian;
    @BindView(R.id.jiesuan)
    Button jiesuan;

    @Override
    protected void initData() {
        DaoMaster.DevOpenHelper li = new DaoMaster.DevOpenHelper(this, "gou");
        DaoMaster master = new DaoMaster(li.getReadableDb());
        DaoSession daoSession = master.newSession();
        GouDao gouDao = daoSession.getGouDao();
        List<Gou> gous = gouDao.loadAll();
        ArrayList<Gou> list = new ArrayList<>();
        for (int i = 0; i < gous.size(); i++) {
            if (gous.get(i).getPosition() > 0) {
                list.add(new Gou(gous.get(i).getId(), gous.get(i).getImage(), gous.get(i).getName(), gous.get(i).getJia(), gous.get(i).getPosition(), gous.get(i).getHe()));
            }
        }
        Gouwu gouwu = new Gouwu(this, list);
        gouwu.notifyDataSetChanged();
        gouwuList.setAdapter(gouwu);
        initqian(list);
        jiesuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GouActivity.this, JieActivity.class);
                intent.putExtra("qian", qian.getText().toString());
                startActivity(intent);
            }
        });
    }
    private void initqian(ArrayList<Gou> list) {
        int a=0;
        for (int i = 0; i <list.size() ; i++) {
            String jia = list.get(i).getJia();
            int he = list.get(i).getPosition();
            int i1 = Integer.parseInt(jia);
            int i3 = i1 * he;
            a=a+i3;
        }
        qian.setText(a+"");
    }
    @Override
    protected void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_gaowu;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
