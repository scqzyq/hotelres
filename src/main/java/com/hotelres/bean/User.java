package com.hotelres.bean;

import java.io.Serializable;

/**
 * @author scqzy
 */
public class User implements Serializable {

    private Integer id;
    private String password;
    private String username;
    private String tel;
    private String idcard;
    private String realname;
    private double jiudianweizhi;
    private double weishengqingjie;
    private double shebeisheshi;
    private double fuwuzhiliang;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public double getJiudianweizhi() {
        return jiudianweizhi;
    }

    public void setJiudianweizhi(double jiudianweizhi) {
        this.jiudianweizhi = jiudianweizhi;
    }

    public double getWeishengqingjie() {
        return weishengqingjie;
    }

    public void setWeishengqingjie(double weishengqingjie) {
        this.weishengqingjie = weishengqingjie;
    }

    public double getShebeisheshi() {
        return shebeisheshi;
    }

    public void setShebeisheshi(double shebeisheshi) {
        this.shebeisheshi = shebeisheshi;
    }

    public double getFuwuzhiliang() {
        return fuwuzhiliang;
    }

    public void setFuwuzhiliang(double fuwuzhiliang) {
        this.fuwuzhiliang = fuwuzhiliang;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", idcard='" + idcard + '\'' +
                ", realname='" + realname + '\'' +
                ", jiudianweizhi=" + jiudianweizhi +
                ", weishengqingjie=" + weishengqingjie +
                ", shebeisheshi=" + shebeisheshi +
                ", fuwuzhiliang=" + fuwuzhiliang +
                '}';
    }
}
