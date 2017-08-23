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
public class SellerBean implements Serializable {
    /**
     * activityList : []
     * deliveryFee :
     * distance :
     * ensure :
     * id : 1
     * invoice :
     * name : 饿啦吗程序员外卖项目
     * pic : http://123.206.14.104:8080/TakeoutService/imgs/category/1.png
     * recentVisit :
     * sale :
     * score : 5
     * sendPrice : 0
     * time :
     */

    private String deliveryFee;
    private String distance;
    private String ensure;
    private int id;
    private String invoice;
    private String name;
    private String pic;
    private String recentVisit;
    private String sale;
    private String score;
    private int sendPrice;
    private String time;
    private List<?> activityList;

    public static SellerBean objectFromData(String str) {

        return new Gson().fromJson(str, SellerBean.class);
    }

    public String getDeliveryFee() {
        return deliveryFee;
    }

    public void setDeliveryFee(String deliveryFee) {
        this.deliveryFee = deliveryFee;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getEnsure() {
        return ensure;
    }

    public void setEnsure(String ensure) {
        this.ensure = ensure;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
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

    public String getRecentVisit() {
        return recentVisit;
    }

    public void setRecentVisit(String recentVisit) {
        this.recentVisit = recentVisit;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public int getSendPrice() {
        return sendPrice;
    }

    public void setSendPrice(int sendPrice) {
        this.sendPrice = sendPrice;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<?> getActivityList() {
        return activityList;
    }

    public void setActivityList(List<?> activityList) {
        this.activityList = activityList;
    }
}
