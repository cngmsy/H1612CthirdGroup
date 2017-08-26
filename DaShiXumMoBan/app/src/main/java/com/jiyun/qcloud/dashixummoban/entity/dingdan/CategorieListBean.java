package com.jiyun.qcloud.dashixummoban.entity.dingdan;

import com.google.gson.Gson;

import java.io.Serializable;

/**
 * 项目负责人：李强
 * 创建时间 2017/8/15.
 * 负责 全部
 * 团队 1人
 */
public class CategorieListBean implements Serializable {
    /**
     * id : 1
     * name : 美食
     * pic : http://123.206.14.104:8080/TakeoutService/imgs/category/1.png
     */

    private int id;
    private String name;
    private String pic;

    public static CategorieListBean objectFromData(String str) {

        return new Gson().fromJson(str, CategorieListBean.class);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
