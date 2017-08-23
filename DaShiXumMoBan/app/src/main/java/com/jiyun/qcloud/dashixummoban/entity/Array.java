package com.jiyun.qcloud.dashixummoban.entity;

/**
 * Created by Administrator on 2017/8/14 0014.
 */

public class Array {
    String aname;
    private String form;
    private String icon;
    private int id;
    private int monthSaleNum;
    private String name;
    private double newPrice;
    private int oldPrice;
    public int position;
    public   int a;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public Array(String aname, String form, String icon, int id, int monthSaleNum, String name, double newPrice, int oldPrice) {
        this.aname = aname;
        this.form = form;
        this.icon = icon;
        this.id = id;
        this.monthSaleNum = monthSaleNum;
        this.name = name;
        this.newPrice = newPrice;
        this.oldPrice = oldPrice;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMonthSaleNum() {
        return monthSaleNum;
    }

    public void setMonthSaleNum(int monthSaleNum) {
        this.monthSaleNum = monthSaleNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public int getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(int oldPrice) {
        this.oldPrice = oldPrice;
    }
}
