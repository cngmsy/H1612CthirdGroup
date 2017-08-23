package com.jiyun.qcloud.dashixummoban.ui.more.more;

import com.jiyun.qcloud.dashixummoban.entity.more.Geng;
import com.jiyun.qcloud.dashixummoban.modle.more.MoreMVModuleImpl;
import com.jiyun.qcloud.dashixummoban.modle.more.MoreMvModule;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class MoreMvPresenter implements MoreMv.Presenter{
    private MoreMvModule moreMvModule;
    private MoreMv.View moreMv;

    public MoreMvPresenter(MoreMv.View moreMv) {
        this.moreMv = moreMv;
        moreMv.setPresenter(this);
        moreMvModule = new MoreMVModuleImpl();
    }


    @Override
    public void start() {
        moreMv.showProgress();
        moreMvModule.loadDingDanList(new NetWorkCallBack<Geng>() {
            @Override
            public void onSuccess(Geng geng) {
                moreMv.showHomeListData(geng);
            }

            @Override
            public void onError(int errorCode, String errorMsg) {

            }

            @Override
            public void onFail(String netOff) {

            }
        });
        moreMv.dimissProgress();
    }
}
