package com.riskmanager.action;

import com.riskmanager.bean.ProjectBean;
import com.riskmanager.bean.RiskBean;
import com.riskmanager.dao.DataBaseDAO;

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

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }

    public String getPlans(){

        dataMap=new HashMap<>();

        dataMap.put("list",Plans_withRisks());

        return "success";
    }

    private ArrayList<Map> Plans_withRisks() {
        //读取数据库的plan
        List<ProjectBean> planList = dataBaseDAO.getAllProjects();
        //转换成map数组
        ArrayList<Map> plans_withRisks=new ArrayList<Map>();

        for(int i=0;i<planList.size();i++){
            HashMap plan=new HashMap();
            plan.put("pid",planList.get(i).getPid());
            plan.put("name",planList.get(i).getName());

            ArrayList<Map> risksList_thisPlan=new ArrayList<Map>();//放对应的risks

            List<RiskBean> risksList = dataBaseDAO.getAllrisk(planList.get(i).getPid());//用id找到对应risks
            for(int j=0;j<risksList.size();++j){//将获得的risks转换成有key的map
                HashMap risk=new HashMap();
                risk.put("rid",risksList.get(j).getRid());
                risk.put("riskTitle",risksList.get(j).getTitle());
                risksList_thisPlan.add(risk);
            }

            plan.put("risks",risksList_thisPlan);

            plans_withRisks.add(plan);
        }
        return  plans_withRisks;
    }
    public String createPlan(){
        return "success";
    }
}
