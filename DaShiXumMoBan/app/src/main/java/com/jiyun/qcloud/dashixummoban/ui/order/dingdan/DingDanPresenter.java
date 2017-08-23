package com.jiyun.qcloud.dashixummoban.ui.order.dingdan;

import com.jiyun.qcloud.dashixummoban.entity.dingdan.Infomaction;
import com.jiyun.qcloud.dashixummoban.modle.dingdan.DingDanModule;
import com.jiyun.qcloud.dashixummoban.modle.dingdan.DingDanModuleImpl;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;
import com.jiyun.qcloud.dashixummoban.ui.order.dingdan.DingDan.Presenter;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class DingDanPresenter implements Presenter {
    private DingDan.View dingDanView;
    private DingDanModule dingDanModule;

    public DingDanPresenter(DingDan.View dingDanView){
        this.dingDanView = dingDanView;
        dingDanView.setPresenter(this);
        dingDanModule = new DingDanModuleImpl();
    }


    @Override
    public void start() {
        dingDanView.showProgress();
        dingDanModule.loadDingDanList(new NetWorkCallBack<Infomaction>() {
            @Override
            public void onSuccess(Infomaction dingDanBean) {
                dingDanView.showHomeListData(dingDanBean);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        });
        dingDanView.dimissProgress();
    }
}
