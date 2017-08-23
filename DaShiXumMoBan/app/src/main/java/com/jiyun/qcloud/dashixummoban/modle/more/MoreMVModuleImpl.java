package com.jiyun.qcloud.dashixummoban.modle.more;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.more.Geng;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 队 1人
 */

public class MoreMVModuleImpl implements MoreMvModule {
    @Override
    public void loadDingDanList(NetWorkCallBack<Geng> loadDingDanList) {
        iHttp.get(Urls.TV,null,loadDingDanList);
    }
}
