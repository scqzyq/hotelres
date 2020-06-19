package com.hotelres.bean;

import java.io.Serializable;

/**
 * @author scqzy
 */
public class Hadmin implements Serializable {
    private Integer aid;
    private String aname;
    private String apassword;

    @Override
    public String toString() {
        return "Hadmin{" +
                "aid=" + aid +
                ", aname='" + aname + '\'' +
                ", apassword='" + apassword + '\'' +
                '}';
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword;
    }
}
