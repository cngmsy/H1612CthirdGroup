package com.jiyun.qcloud.dashixummoban.modle.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

/**
 * Created by Administrator on 2017/8/15 0015.
 */
@Entity
public class Gou {
    @Id
    Long id;
    @Property(nameInDb = "image")
    String image;
    @Property(nameInDb = "name")
    String name;
    @Property(nameInDb = "jia")
    String jia;
    @Property(nameInDb = "position")
    int position;
    @Property(nameInDb = "he")
    String he;
    @Generated(hash = 1469498125)
    public Gou(Long id, String image, String name, String jia, int position,
            String he) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.jia = jia;
        this.position = position;
        this.he = he;
    }
    @Generated(hash = 52686734)
    public Gou() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getImage() {
        return this.image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getJia() {
        return this.jia;
    }
    public void setJia(String jia) {
        this.jia = jia;
    }
    public int getPosition() {
        return this.position;
    }
    public void setPosition(int position) {
        this.position = position;
    }
    public String getHe() {
        return this.he;
    }
    public void setHe(String he) {
        this.he = he;
    }
}
