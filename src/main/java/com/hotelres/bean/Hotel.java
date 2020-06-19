package com.hotelres.bean;

import java.io.Serializable;


/**
 * @author scqzy
 */
public class Hotel implements Serializable {
    private Integer hid;
    private String haddr;
    private String hname;
    private String hlevel;
    private Double jiudianweizhi;
    private Double weishengqingjie;
    private Double shebeisheshi;
    private Double fuwuzhiliang;

    public Integer getHid() {
        return hid;
    }

    public void setHid(Integer hid) {
        this.hid = hid;
    }

    public String getHaddr() {
        return haddr;
    }

    public void setHaddr(String haddr) {
        this.haddr = haddr;
    }

    public String getHname() {
        return hname;
    }

    public void setHname(String hname) {
        this.hname = hname;
    }

    public String getHlevel() {
        return hlevel;
    }

    public void setHlevel(String hlevel) {
        this.hlevel = hlevel;
    }

    public Double getJiudianweizhi() {
        return jiudianweizhi;
    }

    public void setJiudianweizhi(Double jiudianweizhi) {
        this.jiudianweizhi = jiudianweizhi;
    }

    public Double getWeishengqingjie() {
        return weishengqingjie;
    }

    public void setWeishengqingjie(Double weishengqingjie) {
        this.weishengqingjie = weishengqingjie;
    }

    public Double getShebeisheshi() {
        return shebeisheshi;
    }

    public void setShebeisheshi(Double shebeisheshi) {
        this.shebeisheshi = shebeisheshi;
    }

    public Double getFuwuzhiliang() {
        return fuwuzhiliang;
    }

    public void setFuwuzhiliang(Double fuwuzhiliang) {
        this.fuwuzhiliang = fuwuzhiliang;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "hid=" + hid +
                ", haddr='" + haddr + '\'' +
                ", hname='" + hname + '\'' +
                ", hlevel='" + hlevel + '\'' +
                ", jiudianweizhi=" + jiudianweizhi +
                ", weishengqingjie=" + weishengqingjie +
                ", shebeisheshi=" + shebeisheshi +
                ", fuwuzhiliang=" + fuwuzhiliang +
                '}';
    }
}
