package com.riskmanager.action;

import com.riskmanager.bean.*;
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
        List<RiskBean> list = dataBaseDAO.getAllrisk();


        dataMap.put("list",changeToRiskVos(list));
        dataMap.put("group", webContext.getGroup());
        return "success";
    }

    public List<RiskVO> changeToRiskVos(List<RiskBean> list){
        List<RiskVO> returnList= new ArrayList<>();

        for (int i=0;i<list.size();i++){
            RiskBean riskBean = list.get(i);
            RiskVO riskVO = new RiskVO();
            riskVO.setRid(riskBean.getRid());
            RiskDetailBean riskDetailBean = riskBean.getDetails().get(0);
            riskVO.setCreator(riskBean.getCreator());

            riskVO.setTime(riskDetailBean.getUpdateTime());
            riskVO.setRiskTitle(riskDetailBean.getRiskTitle());
            riskVO.setContent(riskDetailBean.getContent());
            riskVO.setThreshold(riskDetailBean.getThreshold());
            riskVO.setRiskPossibility(riskDetailBean.getRiskPossibility());
            riskVO.setRiskInfluence(riskDetailBean.getRiskInfluence());

            List<RiskDetailBean> riskDetailBeans = riskBean.getDetails();

            ArrayList<HistoryVO> historyVOs = new ArrayList<>();

            for (int j=0;j<riskDetailBeans.size();j++){
                RiskDetailBean riskDetailBean1 = riskDetailBeans.get(i);
                HistoryVO historyVO = new HistoryVO();

                historyVO.setRiskInfluence(riskDetailBean1.getRiskInfluence());
                historyVO.setTime(riskDetailBean1.getUpdateTime());
                historyVO.setRiskPossibility(riskDetailBean1.getRiskPossibility());
                historyVO.setThreshold(riskDetailBean1.getThreshold());
                historyVO.setContent(riskDetailBean1.getContent());
                historyVO.setRiskTitle(riskDetailBean1.getRiskTitle());
                historyVO.setUserid(riskDetailBean1.getUpdater());
                historyVOs.add(historyVO);
            }

            riskVO.setHistory(historyVOs);

            returnList.add(riskVO);
        }
        return returnList;
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


//                if (riskBean == null ||  !find(Integer.parseInt(String.valueOf(lines[0])),arrayList)) {
//                    riskBean = new RiskBean(
//                            Integer.parseInt(String.valueOf(lines[0])),
//                            String.valueOf(lines[1]),
//                            String.valueOf(lines[2]),
//                            String.valueOf(lines[3]),
//                            String.valueOf(lines[4]),
//                            String.valueOf(lines[5]),
//                            String.valueOf(lines[6]),
//                            new ArrayList<>()
//                    );
//
//                    arrayList.add(riskBean);
//                    if (lines[7]!=null){
//                        riskBean.getTracker().add(new TrackerBean(String.valueOf(lines[8])));
//                        continue;
//                    }
//
//                }else {
//                    if (lines[7]!=null){
//                        riskBean.getTracker().add(new TrackerBean(String.valueOf(lines[8])));
//                    }
//                }

                i++;
            }
        }


        return  arrayList;
    }

    private boolean find(int id, ArrayList<RiskBean> arrayList){
        if (arrayList.get(arrayList.size()-1).getRid()==id)
            return true;
        else
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
    private String newTracker;

    public String getNewTracker() {
        return newTracker;
    }

    public void setNewTracker(String newTracker) {
        this.newTracker = newTracker;
    }

    public String modify(){
        dataMap=new HashMap<>();
        RiskDetailBean riskDetailBean = new RiskDetailBean();

        if (rid == -1){
            rid = dataBaseDAO.insertRisk(new RiskBean(webContext.getUserName()));
        }
        riskDetailBean.setRid(rid);

        riskDetailBean.setContent(content);
        riskDetailBean.setRiskInfluence(riskInfluence);
        riskDetailBean.setThreshold(threshold);
        riskDetailBean.setRiskPossibility(riskPossibility);
        riskDetailBean.setUpdater(webContext.getUserName());
        riskDetailBean.setRiskTitle(riskTitle);
        dataBaseDAO.insertRiskDetail(riskDetailBean);
        dataMap.put("msg","success");
        return "success";
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

    private int pid;
    public String importRisk(){
        dataBaseDAO.insertRiskProject(rid,pid);
        return "success";
    }
}
