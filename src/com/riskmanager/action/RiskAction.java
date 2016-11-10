package com.riskmanager.action;

import com.riskmanager.bean.RiskBean;
import com.riskmanager.bean.TrackerBean;
import com.riskmanager.dao.DataBaseDAO;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenghao on 2016/11/10.
 */
public class RiskAction {
    private Map<String, Object> dataMap;

    @Resource
    private DataBaseDAO dataBaseDAO;

    public String getAllRisk(){
        System.out.println("debug execute");
        dataMap=new HashMap<>();
        dataMap.put("list",changeToRiskBean(dataBaseDAO.getAllrisk()));
        return "success";
    }

    public Map<String, Object> getDataMap() {
        return dataMap;
    }

    private ArrayList<RiskBean> changeToRiskBean(List<Object[]> list) {
        ArrayList<RiskBean> arrayList = new ArrayList<>();

        if (list != null) {
            int i = 0;
            RiskBean riskBean = null;
            while (i < list.size()) {

                Object[] lines = list.get(i);


                if (riskBean == null || Integer.parseInt(String.valueOf(lines[0])) != riskBean.getRid()) {
                    riskBean = new RiskBean(
                            Integer.parseInt(String.valueOf(lines[0])),
                            String.valueOf(lines[1]),
                            String.valueOf(lines[2]),
                            String.valueOf(lines[3]),
                            String.valueOf(lines[4]),
                            String.valueOf(lines[5]),
                            String.valueOf(lines[6]),
                            new ArrayList<>()
                    );

                    arrayList.add(riskBean);
                }else {
                    if (lines[7]!=null){
                        riskBean.getList().add(new TrackerBean(String.valueOf(lines[8])));
                    }
                }

                i++;
            }
        }


        return  arrayList;
    }
}
