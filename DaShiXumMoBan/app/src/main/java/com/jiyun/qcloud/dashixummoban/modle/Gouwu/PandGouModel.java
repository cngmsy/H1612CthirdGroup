package com.jiyun.qcloud.dashixummoban.modle.Gouwu;

import com.jiyun.qcloud.dashixummoban.config.Urls;
import com.jiyun.qcloud.dashixummoban.entity.Hom2Ben;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * Created by Administrator on 2017/8/21 0021.
 */

public class PandGouModel implements IPandGouModel {
    @Override
    public void loadHomeList(NetWorkCallBack<Hom2Ben> callback) {
        iHttp.get(Urls.EVEN,null,callback);
    }
}
