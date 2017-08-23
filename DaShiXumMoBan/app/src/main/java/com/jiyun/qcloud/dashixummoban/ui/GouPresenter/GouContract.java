package com.jiyun.qcloud.dashixummoban.ui.GouPresenter;

import com.jiyun.qcloud.dashixummoban.app.App;
import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.Hom2Ben;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class GouContract  {
    public interface View extends IBaseView<Presenter> {
        void showHomeListData(Hom2Ben hom2Ben);
        void playVideo();
        void loadWebView();
    }
    public interface Presenter extends IBasePresenter {}
}
