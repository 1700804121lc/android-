package com.yy.entity;

import java.io.Serializable;

/**
 * 课程信息类
 */
public class Yiyuan implements Serializable {

    private int yid;//医院id
    private String name;//医院名字
    private String jianjie;//医院简介
    private String phone;//医院内容
    private String dizhi;//医院内容
    private String icon;//医院内容

    public int getYid() {
        return yid;
    }

    public void setYid(int yid) {
        this.yid = yid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJianjie() {
        return jianjie;
    }

    public void setJianjie(String jianjie) {
        this.jianjie = jianjie;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDizhi() {
        return dizhi;
    }

    public void setDizhi(String dizhi) {
        this.dizhi = dizhi;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Yiyuan() {
    }

    public Yiyuan(int yid, String name, String jianjie, String phone, String dizhi, String icon) {
        this.yid = yid;
        this.name = name;
        this.jianjie = jianjie;
        this.phone = phone;
        this.dizhi = dizhi;
        this.icon = icon;
    }
}
