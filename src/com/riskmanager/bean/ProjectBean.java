package com.riskmanager.bean;

import java.util.List;

/**
 * Created by chenghao on 2016/11/20.
 */
public class ProjectBean {
    private int pid;
    private String name;
    private String creator;
    private String createTime;

    private List<RiskBean> riskBeen;

    public List<RiskBean> getRiskBeen() {
        return riskBeen;
    }

    public void setRiskBeen(List<RiskBean> riskBeen) {
        this.riskBeen = riskBeen;
    }

    public ProjectBean(int pid, String name, String creator, String createTime, List<RiskBean> riskBeen) {
        this.pid = pid;
        this.name = name;
        this.creator = creator;
        this.createTime = createTime;
        this.riskBeen = riskBeen;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public ProjectBean(){

    }

    public ProjectBean(int pid, String name, String creator, String createTime) {
        this.pid = pid;
        this.name = name;
        this.creator = creator;
        this.createTime = createTime;
    }
}
