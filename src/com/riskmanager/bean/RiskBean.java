package com.riskmanager.bean;


import java.util.ArrayList;

/**
 * Created by chenghao on 2016/11/10.
 */
public class RiskBean {
    private int rid;
    private int pid;
    private String creator;
    private String createTime;

    private ArrayList<RiskDetailBean> details;

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public RiskBean(int rid, int pid, String creator, String createTime, ArrayList<RiskDetailBean> details) {
        this.rid = rid;
        this.pid = pid;
        this.creator = creator;
        this.createTime = createTime;
        this.details = details;
    }

    public ArrayList<RiskDetailBean> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<RiskDetailBean> details) {
        this.details = details;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public RiskBean(int rid, String creator, String createTime, ArrayList<RiskDetailBean> details) {
        this.rid = rid;
        this.creator = creator;
        this.createTime = createTime;
        this.details = details;
    }

    public RiskBean() {
    }
}
