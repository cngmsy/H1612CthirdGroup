package com.jiyun.qcloud.dashixummoban.ui.order.dingdan;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.Infomaction;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class DingDan {
    public interface View extends IBaseView<Presenter> {
        void showHomeListData(Infomaction dingDanBean);
    }

    public interface Presenter extends IBasePresenter {}
}
