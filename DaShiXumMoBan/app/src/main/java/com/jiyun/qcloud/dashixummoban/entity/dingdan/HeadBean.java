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
public class HeadBean implements Serializable {
    private List<CategorieListBean> categorieList;
    private List<PromotionListBean> promotionList;

    public static HeadBean objectFromData(String str) {

        return new Gson().fromJson(str, HeadBean.class);
    }

    public List<CategorieListBean> getCategorieList() {
        return categorieList;
    }

    public void setCategorieList(List<CategorieListBean> categorieList) {
        this.categorieList = categorieList;
    }

    public List<PromotionListBean> getPromotionList() {
        return promotionList;
    }

    public void setPromotionList(List<PromotionListBean> promotionList) {
        this.promotionList = promotionList;
    }
}
