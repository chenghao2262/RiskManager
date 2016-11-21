package com.riskmanager.action;

import java.util.Map;

/**
 * Created by chenghao on 2016/11/21.
 */
public class PlanAction {
    private Map<String, Object> dataMap;

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public String getPlans(){
        return "success";
    }

    public String createPlan(){
        return "success";
    }
}
