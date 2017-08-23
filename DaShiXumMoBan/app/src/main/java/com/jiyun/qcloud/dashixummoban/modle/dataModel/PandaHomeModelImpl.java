package com.jiyun.qcloud.dashixummoban.modle.dataModel;


import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.HomeBean;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by xingge on 2017/7/26.
 */

public class PandaHomeModelImpl implements IPandaHomeModel {



    @Override
    public void loadHomeList(NetWorkCallBack<HomeBean> callback) {
        iHttp.get(Urls.ELEMEHOME,null,callback);
    }
}
