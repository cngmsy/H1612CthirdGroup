package com.jiyun.qcloud.dashixummoban.main;

/**
 * Created by dell on 2017/8/22.
 */

public class UserBean {
    private String url;
    private String name;

    public UserBean(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public UserBean() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
