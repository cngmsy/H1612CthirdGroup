package com.jiyun.qcloud.dashixummoban.modle.dingdan;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.dingdan.Infomaction;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/21.
 * 负责 全部
 * 团队 1人
 */

public class DingDanModuleImpl implements DingDanModule {
    @Override
    public void loadDingDanList(NetWorkCallBack<Infomaction> callBack) {
        iHttp.get(Urls.DINGDAN,null,callBack);
    }
}
