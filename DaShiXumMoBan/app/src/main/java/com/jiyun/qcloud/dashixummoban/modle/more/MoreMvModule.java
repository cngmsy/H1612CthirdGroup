package com.jiyun.qcloud.dashixummoban.modle.more;

import com.jiyun.qcloud.dashixummoban.entity.more.Geng;
import com.jiyun.qcloud.dashixummoban.modle.dataModel.BaseModel;
import com.jiyun.qcloud.dashixummoban.modle.net.callback.NetWorkCallBack;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/22.
 * 负责 全部
 * 团队 1人
 */

public interface MoreMvModule extends BaseModel {
    void loadDingDanList(NetWorkCallBack<Geng> loadDingDanList);
}
