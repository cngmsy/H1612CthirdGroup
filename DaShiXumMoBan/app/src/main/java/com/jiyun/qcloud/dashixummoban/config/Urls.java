package com.jiyun.qcloud.dashixummoban.config;

/**
 * Created by xingge on 2017/7/11.
 * 相关参数
 */

public class Urls {


    //服务器地址
    private static final String BASEURL = "http://123.206.14.104:8080/";
    //首页
    public static final String ELEMEHOME = BASEURL + "TakeoutService/home?latitude=116.30142&longitude=40.05087";
    //首页
    public static final String PANDAHOME = BASEURL+"PAGE14501749764071042/index.json";
    //熊猫直播
    public static final String PANDALIVE = BASEURL+"PAGE14501769230331752/index.json";
    //列表
    public static final String PAGELIST = BASEURL+"PAGE14501786751053212/index.json";

    public static final String PAGEINFOLIST = "http://101.200.142.201/MyListLoadAuto/listload";
    //获取图片验证码
    public static final String IMGCODE = "http://reg.cntv.cn/simple/verificationCode.action";
    //邮箱注册
    public static final String EMAILREGISTER = "https://reg.cntv.cn/api/register.action";

    //    订单   http://123.206.14.104:8080/TakeoutService/order?userId=3626
    public static final String DINGDAN = "http://123.206.14.104:8080/TakeoutService/home?latitude=116.30142&longitude=40.05087";
    //    视屏
    public static final String TV = "https://ticket-api-m.mtime.cn/discovery/trailerList.api";

    public static final String EVEN="http://123.206.14.104:8080/TakeoutService/goods?sellerId=101";
}
