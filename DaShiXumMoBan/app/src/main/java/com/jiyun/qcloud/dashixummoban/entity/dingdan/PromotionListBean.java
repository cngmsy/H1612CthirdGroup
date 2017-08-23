package com.jiyun.qcloud.dashixummoban.entity.dingdan;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/15.
 * 负责 全部
 * 团队 1人
 */
public class PromotionListBean implements Serializable {
    /**
     * id : 1
     * info : promotion info...
     * pic : http://123.206.14.104:8080/TakeoutService/imgs/promotion/1.jpg
     */

    private int id;
    private String info;
    private String pic;

    public static PromotionListBean objectFromData(String str) {

        return new Gson().fromJson(str, PromotionListBean.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
