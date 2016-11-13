package com.riskmanager.bean;


import java.util.ArrayList;

/**
 * Created by chenghao on 2016/11/10.
 */
public class RiskBean {
    private int rid;
    private String riskTitle;
    private String riskPossibility;
    private String riskInfluence;
    private String threshold;
    private String content;
    private String creator;
    private int uid;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    private ArrayList<TrackerBean> list;

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ArrayList<TrackerBean> getList() {
        return list;
    }

    public void setList(ArrayList<TrackerBean> list) {
        this.list = list;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public RiskBean() {
    }

    public RiskBean(int rid, String riskTitle, String riskPossibility, String riskInfluence, String threshold, String content, String creator, ArrayList<TrackerBean> list) {
        this.rid = rid;
        this.riskTitle = riskTitle;
        this.riskPossibility = riskPossibility;
        this.riskInfluence = riskInfluence;
        this.threshold = threshold;
        this.content = content;
        this.creator = creator;
        this.list = list;
    }

    public RiskBean(int rid,String riskTitle, String riskPossibility, String riskInfluence, String threshold, String content, String creator) {
        this.rid = rid;
        this.riskTitle = riskTitle;
        this.riskPossibility = riskPossibility;
        this.riskInfluence = riskInfluence;
        this.threshold = threshold;
        this.content = content;
        this.creator = creator;
    }
}
