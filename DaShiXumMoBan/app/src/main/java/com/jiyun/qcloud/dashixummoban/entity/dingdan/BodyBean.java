package com.jiyun.qcloud.dashixummoban.entity.dingdan;

import com.google.gson.Gson;

import java.io.Serializable;
import java.util.List;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/15.
 * 负责 全部
 * 团队 1人
 */
public class BodyBean implements Serializable {
    /**
     * recommendInfos : []
     * seller : {"activityList":[],"deliveryFee":"","distance":"","ensure":"","id":1,"invoice":"","name":"饿啦吗程序员外卖项目","pic":"http://123.206.14.104:8080/TakeoutService/imgs/category/1.png","recentVisit":"","sale":"","score":"5","sendPrice":0,"time":""}
     * type : 0
     */

    private SellerBean seller;
    private int type;
    private List<?> recommendInfos;

    public static BodyBean objectFromData(String str) {

        return new Gson().fromJson(str, BodyBean.class);
    }

    public SellerBean getSeller() {
        return seller;
    }

    public void setSeller(SellerBean seller) {
        this.seller = seller;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public List<?> getRecommendInfos() {
        return recommendInfos;
    }

    public void setRecommendInfos(List<?> recommendInfos) {
        this.recommendInfos = recommendInfos;
    }
}
