package com.riskmanager.bean;

import java.util.ArrayList;

/**
 * Created by chenghao on 2016/11/30.
 */
public class RiskVO {
    private int rid;
    private String time;
    private String riskTitle;
    private String riskPossibility;
    private String riskInfluence;
    private String threshold;
    private String content;
    private String creator;

    private ArrayList<HistoryVO> history;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

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

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public ArrayList<HistoryVO> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<HistoryVO> history) {
        this.history = history;
    }


}
