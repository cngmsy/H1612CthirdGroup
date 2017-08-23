package com.jiyun.qcloud.dashixummoban.ui.more.more;

import com.jiyun.qcloud.dashixummoban.base.IBasePresenter;
import com.jiyun.qcloud.dashixummoban.base.IBaseView;
import com.jiyun.qcloud.dashixummoban.entity.more.Geng;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public class MoreMv {

    public interface View extends IBaseView<Presenter> {
        void showHomeListData(Geng geng);
    }
    public interface  Presenter extends IBasePresenter {}
}
