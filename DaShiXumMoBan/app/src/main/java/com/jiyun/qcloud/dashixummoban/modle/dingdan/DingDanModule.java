package com.jiyun.qcloud.dashixummoban.modle.dingdan;

import com.jiyun.qcloud.dashixummoban.entity.dingdan.Infomaction;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/21.
 * 负责 全部
 * 团队 1人
 */

public interface DingDanModule extends BaseModel {
    void loadDingDanList(NetWorkCallBack<Infomaction> callBack);
}
