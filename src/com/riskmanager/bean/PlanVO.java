package com.riskmanager.bean;

import java.util.List;

/**
 * Created by chenghao on 2016/11/30.
 */
public class PlanVO {
    private int pid;
    private String name;
    private List<RiskVO> riskList;

    public PlanVO(int pid, String name, List<RiskVO> riskList) {
        this.pid = pid;
        this.name = name;
        this.riskList = riskList;
    }

    public PlanVO() {
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

    public List<RiskVO> getRiskList() {
        return riskList;
    }

    public void setRiskList(List<RiskVO> riskList) {
        this.riskList = riskList;
    }
}

