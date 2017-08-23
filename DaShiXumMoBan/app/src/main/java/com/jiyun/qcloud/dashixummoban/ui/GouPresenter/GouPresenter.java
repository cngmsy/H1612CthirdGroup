package com.jiyun.qcloud.dashixummoban.ui.GouPresenter;

import com.jiyun.qcloud.dashixummoban.entity.Hom2Ben;
import com.jiyun.qcloud.dashixummoban.modle.Gouwu.IPandGouModel;
import com.jiyun.qcloud.dashixummoban.modle.Gouwu.PandGouModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class GouPresenter implements GouContract.Presenter {
   private GouContract.View gouView;
   private IPandGouModel pandGouModel;

    public GouPresenter(GouContract.View gouView) {
        this.gouView = gouView;
        gouView.setPresenter(this);
        this.pandGouModel=new PandGouModel();
    }

    @Override
    public void start() {
            gouView.showProgress();
        pandGouModel.loadHomeList(new NetWorkCallBack<Hom2Ben>() {
            @Override
            public void onSuccess(Hom2Ben hom2Ben) {
                gouView.showHomeListData(hom2Ben);
                gouView.dimissProgress();
            }

            @Override
            public void onError(int errorCode, String errorMsg) {
                gouView.showMessage(errorMsg);
                gouView.dimissProgress();
            }

            @Override
            public void onFail(String netOff) {

            }
        });
    }
}
