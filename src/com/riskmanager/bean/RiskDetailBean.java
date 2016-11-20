package com.riskmanager.bean;

/**
 * Created by chenghao on 2016/11/20.
 */
public class RiskDetailBean {
    private int rid;
    private int rdid;
    private String updateTime;
    private String updater;
    private String riskTitle;
    private String riskPossibility;
    private String riskInfluence;
    private String threshold;
    private String content;

    public RiskDetailBean(){

    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getRdid() {
        return rdid;
    }

    public void setRdid(int rdid) {
        this.rdid = rdid;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater;
    }

    public String getRiskTitle() {
        return riskTitle;
    }

    public void setRiskTitle(String riskTitle) {
        this.riskTitle = riskTitle;
    }

    public String getRiskPossibility() {
        return riskPossibility;
    }

    public void setRiskPossibility(String riskPossibility) {
        this.riskPossibility = riskPossibility;
    }

    public String getRiskInfluence() {
        return riskInfluence;
    }

    public void setRiskInfluence(String riskInfluence) {
        this.riskInfluence = riskInfluence;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public RiskDetailBean(int rid, int rdid, String updateTime, String updater, String riskTitle, String riskPossibility, String riskInfluence, String threshold, String content) {
        this.rid = rid;
        this.rdid = rdid;
        this.updateTime = updateTime;
        this.updater = updater;
        this.riskTitle = riskTitle;
        this.riskPossibility = riskPossibility;
        this.riskInfluence = riskInfluence;
        this.threshold = threshold;
        this.content = content;
    }
}
