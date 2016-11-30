package com.riskmanager.bean;

/**
 * Created by chenghao on 2016/11/30.
 */
public class HistoryVO {
    private String time;
    private String riskTitle;
    private String riskPossibility;
    private String riskInfluence;
    private String threshold;
    private String content;

    private String userid;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public HistoryVO(String time, String riskTitle, String riskPossibility, String riskInfluence, String threshold, String content, String userid) {
        this.time = time;
        this.riskTitle = riskTitle;
        this.riskPossibility = riskPossibility;
        this.riskInfluence = riskInfluence;
        this.threshold = threshold;
        this.content = content;
        this.userid = userid;
    }

    public HistoryVO() {
    }
}
