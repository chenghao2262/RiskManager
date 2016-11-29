package com.riskmanager.action;

import com.riskmanager.bean.ProjectBean;
import com.riskmanager.bean.RiskBean;
import com.riskmanager.dao.DataBaseDAO;
import com.riskmanager.dao.WebContext;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenghao on 2016/11/21.
 */
public class PlanAction {
    private Map<String, Object> dataMap;
    @Resource
    private DataBaseDAO dataBaseDAO;
    @Resource
    private WebContext webContext;
    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public String getPlans(){

        dataMap=new HashMap<>();
        List<ProjectBean> list = dataBaseDAO.getAllProjects();

        for (int i=0;i<list.size();i++){
            list.get(i).setRiskBeen(dataBaseDAO.getAllrisk(list.get(i).getPid()));
        }

        dataMap.put("list",list);

        return "success";
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String createPlan(){
        ProjectBean projectBean = new ProjectBean();
        projectBean.setName(name);
        projectBean.setCreator(webContext.getUserName());
        dataBaseDAO.insertProject(projectBean);
        return "success";
    }
}
