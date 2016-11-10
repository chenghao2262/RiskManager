package com.riskmanager.bean;

/**
 * Created by chenghao on 2016/11/10.
 */
public class TrackerBean {
    private String userid;

    public TrackerBean(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }
}
