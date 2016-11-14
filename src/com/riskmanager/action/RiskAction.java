package com.riskmanager.action;

import com.riskmanager.bean.RiskBean;
import com.riskmanager.bean.TrackerBean;
import com.riskmanager.dao.DataBaseDAO;
import com.riskmanager.dao.WebContext;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.taglibs.standard.tag.el.sql.UpdateTag;

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
    @Resource
    private WebContext webContext;
    private int rid;


    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getAllRisk(){
        System.out.println("debug getAllRisk execute");
        dataMap=new HashMap<>();
        dataMap.put("list",changeToRiskBean(dataBaseDAO.getAllrisk()));
        dataMap.put("group", webContext.getGroup());
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


                if (riskBean == null ||  !find(Integer.parseInt(String.valueOf(lines[0])),arrayList)) {
                    riskBean = new RiskBean(
                            Integer.parseInt(String.valueOf(lines[0])),
                            String.valueOf(lines[1]),
                            String.valueOf(lines[2]),
                            String.valueOf(lines[3]),
                            String.valueOf(lines[4]),
                            String.valueOf(lines[5]),
                            new ArrayList<>()
                    );

                    arrayList.add(riskBean);
                }else {
                    if (lines[6]!=null){
                        riskBean.getTracker().add(new TrackerBean(String.valueOf(lines[7])));
                    }
                }

                i++;
            }
        }


        return  arrayList;
    }

    private boolean find(int id, ArrayList<RiskBean> arrayList){
        for (int i=0;i<arrayList.size();i++){
            if (id==arrayList.get(i).getRid()){
                return true;
            }
        }
        return  false;
    }

    public String del(){
        System.out.println("debug del execute");
        dataMap=new HashMap<>();

        RiskBean riskBean = dataBaseDAO.getRiskBean(rid);

        dataBaseDAO.remove(rid);


        dataMap.put("msg","success");
        return "success";
    }

    private String riskTitle;
    private String riskPossibility;
    private String riskInfluence;
    private String content;
    private String threshold;

    public String modify(){
        dataMap=new HashMap<>();
        if (rid == -1){
            insert(riskTitle,riskPossibility,riskInfluence,content,threshold);
        }else{
            update(rid,riskTitle,riskPossibility,riskInfluence,content,threshold);
        }
        dataMap.put("msg","success");
        return "success";
    }

    private void update(int rid, String riskTitle, String riskPossibility, String riskInfluence, String content, String threshold) {
        dataBaseDAO.update(rid,riskTitle,riskPossibility,riskInfluence,content,threshold);
    }

    private void insert(String riskTitle, String riskPossibility, String riskInfluence, String content, String threshold) {
        dataBaseDAO.insert(riskTitle,riskPossibility,riskInfluence,content,threshold);
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThreshold() {
        return threshold;
    }

    public void setThreshold(String threshold) {
        this.threshold = threshold;
    }

    public void setDataMap(Map<String, Object> dataMap) {
        this.dataMap = dataMap;
    }
}
