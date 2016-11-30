package com.riskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.riskmanager.bean.*;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.apache.commons.dbutils.wrappers.StringTrimmedResultSet;
import org.springframework.stereotype.Repository;

@Repository
public class DataBaseDAO {

	@Resource
	private JdbcUtils utils;

	private String sql = "jdbc:mysql://59.110.10.18:3306/riskmanager?"
			+ "user=root&password=root&useUnicode=true&characterEncoding=UTF8";

	@Resource
	private WebContext webContext;

    public ArrayList<RiskBean> getAllrisk() {
        // dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据

        QueryRunner queryRunner = new QueryRunner();
        Connection connection=utils.getConnection();
        try {
            List<Object[]> list = queryRunner.query(connection, "select * from risk left join risk_detail  on risk.rid=risk_detail.rid order by risk.rid,risk_detail.updateTime desc",
                    new ArrayListHandler());
            return changeToAllRiskBean(list);
        }    //加载JDBC驱动
        catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

	public List<Object[]> getUpdateTimes(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=utils.getConnection();
		try {
			List<Object[]> list = queryRunner.query(connection, "select risk.rid,count(risk_detail.rid) as times from risk left join risk_detail  on risk.rid=risk_detail.rid group by risk.rid order by times desc",
					new ArrayListHandler());
			return list;
		}    //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<Object[]> getYinyong(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=utils.getConnection();
		try {
			List<Object[]> list = queryRunner.query(connection, "select rid,count(*) as times from risk_project group by rid order by times",
					new ArrayListHandler());
			return list;
		}    //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public ArrayList<RiskBean> getAllrisk(int pid) {
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据

		QueryRunner queryRunner = new QueryRunner();
		Connection connection=utils.getConnection();
		try {
			List<Object[]> list = queryRunner.query(connection, "select * from (select risk.* from risk_project,risk where risk_project.pid=? and risk_project.rid=risk.rid) as t left join risk_detail on t.rid=risk_detail.rid   order by t.rid,risk_detail.updateTime desc",
					new ArrayListHandler(),pid);
			return changeToAllRiskBean(list);
		}    //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public List<ProjectBean> getAllProjects(){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=utils.getConnection();
		try {
			return queryRunner.query(connection, "select * from project",
					new BeanListHandler<ProjectBean>(ProjectBean.class));
		}    //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public UserBean getUserBeanByName(String name) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=utils.getConnection();
		try {
			UserBean userBean = queryRunner.query(connection, "select * from user where username=?",
					new BeanHandler<>(UserBean.class),new String[]{name});
			System.out.println(userBean);
			return userBean;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}


	public void remove(int rid) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=null;
		try {
			connection = utils.getConnection();    //创建数据库连接对象
			queryRunner.update(connection, "delete from risk where rid=?",new Object[]{rid});
		}     //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	//获得某个特定风向
	public RiskBean getRiskBean(int rid) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=null;
		try {
			connection = utils.getConnection();    //创建数据库连接对象
			return changeToRiskBean(queryRunner.query(connection,"select * from risk,risk_detail where risk.rid=risk_detail.rid and risk.rid=?",new ArrayListHandler(),rid));
		}     //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	private RiskBean changeToRiskBean(List<Object[]> lists){
        Object[] objects = lists.get(0);

        RiskBean riskBean = new RiskBean();
        riskBean.setRid(Integer.parseInt(String.valueOf(objects[0])));
        riskBean.setCreateTime(String.valueOf(objects[1]));
        riskBean.setCreator(String.valueOf(objects[2]));
        riskBean.setDetails(new ArrayList<>());
        for (int i=0;i<lists.size();i++){
            Object[] rds = lists.get(i);
            RiskDetailBean riskDetailBean = new RiskDetailBean();
            riskDetailBean.setRid(Integer.parseInt(String.valueOf(rds[3])));
            riskDetailBean.setRdid(Integer.parseInt(String.valueOf(rds[4])));
            riskDetailBean.setUpdateTime(String.valueOf(rds[5]));
            riskDetailBean.setUpdater(String.valueOf(rds[6]));
            riskDetailBean.setRiskTitle(String.valueOf(rds[7]));
            riskDetailBean.setRiskPossibility(String.valueOf(rds[8]));
            riskDetailBean.setRiskInfluence(String.valueOf(rds[9]));
            riskDetailBean.setThreshold(String.valueOf(rds[10]));
            riskDetailBean.setContent(String.valueOf(rds[11]));
            riskBean.getDetails().add(riskDetailBean);
        }

		return riskBean;
	}

    private ArrayList<RiskBean> changeToAllRiskBean(List<Object[]> lists){
        ArrayList<RiskBean> arrayList = new ArrayList<>();
        RiskBean riskBean = null;
        for (int i=0;i<lists.size();i++){
            Object[] objects = lists.get(i);
            int rid = Integer.parseInt(String.valueOf(objects[0]));
            if (riskBean==null || riskBean.getRid() != rid){
                riskBean = new RiskBean();
                riskBean.setRid(rid);
                riskBean.setCreateTime(String.valueOf(objects[1]));
                riskBean.setCreator(String.valueOf(objects[2]));
                riskBean.setDetails(new ArrayList<>());
                arrayList.add(riskBean);
            }

            Object[] rds = lists.get(i);
            RiskDetailBean riskDetailBean = new RiskDetailBean();
            riskDetailBean.setRid(Integer.parseInt(String.valueOf(rds[3])));
            riskDetailBean.setRdid(Integer.parseInt(String.valueOf(rds[4])));
            riskDetailBean.setUpdateTime(String.valueOf(rds[5]));
            riskDetailBean.setUpdater(String.valueOf(rds[6]));
            riskDetailBean.setRiskTitle(String.valueOf(rds[7]));
            riskDetailBean.setRiskPossibility(String.valueOf(rds[8]));
            riskDetailBean.setRiskInfluence(String.valueOf(rds[9]));
            riskDetailBean.setThreshold(String.valueOf(rds[10]));
            riskDetailBean.setContent(String.valueOf(rds[11]));
            riskBean.getDetails().add(riskDetailBean);
        }

        return arrayList;
    }

//	public void update(int rid, String riskTitle, String riskPossibility, String riskInfluence, String content, String threshold, String newTracker) {
//		QueryRunner queryRunner = new QueryRunner();
//		Connection connection=null;
//		try {
//
//			connection = utils.getConnection();    //创建数据库连接对象
//			queryRunner.update(connection, "update risk set riskTitle=?,riskPossibility=?,riskInfluence=?,threshold=?,content=?  where rid=?",new Object[]{riskTitle,riskPossibility,riskInfluence,threshold,content, rid});
//			queryRunner.update(connection, "insert into risk_tracker values(?,?)",new Object[]{rid,newTracker});
//		}     //加载JDBC驱动
//		catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void insert(String riskTitle, String riskPossibility, String riskInfluence, String content, String threshold) {
//		QueryRunner queryRunner = new QueryRunner();
//		Connection connection=null;
//		try {
//			connection = utils.getConnection();    //创建数据库连接对象
//			queryRunner.update(connection, "insert into risk(riskTitle,riskPossibility,riskInfluence,threshold,content,creator) values(?,?,?,?,?,?)",new Object[]{riskTitle,riskPossibility,riskInfluence,content,threshold,webContext.getUserName()});
//			queryRunner.update(connection, "insert into risk_tracker values(last_insert_id(),?)",new Object[]{webContext.getUserName()});
//		}     //加载JDBC驱动
//		catch (SQLException e) {
//			e.printStackTrace();
//		}finally {
//			try {
//				connection.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//	}
	//新增新计划
	public int insertProject(ProjectBean projectBean){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into project(name,creator,createTime) values(?,?,now())", new Object[]{projectBean.getName(),projectBean.getCreator()});
			Object pid = queryRunner.query(connection,"select last_insert_id()", new ScalarHandler<>() );
			return Integer.parseInt(String.valueOf(pid));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}


	// 新增风险
	public int insertRisk(RiskBean riskBean){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into risk(creator,createTime) values(?,now())", new Object[]{riskBean.getCreator()});
			Object pid = queryRunner.query(connection,"select last_insert_id()", new ScalarHandler<>() );
			return Integer.parseInt(String.valueOf(pid));
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return -1;
	}

	//增加风险修改详细
	public void insertRiskDetail(RiskDetailBean riskDetailBean){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into risk_detail(rid,updateTime,updater,riskTitle,riskPossibility,riskInfluence,threshold,content) values(?,now(),?,?,?,?,?,?)", new Object[]{riskDetailBean.getRid(),riskDetailBean.getUpdater(),riskDetailBean.getRiskTitle(),riskDetailBean.getRiskPossibility(),riskDetailBean.getRiskInfluence(),riskDetailBean.getThreshold(),riskDetailBean.getContent()});
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	// 增加风险计划对应项
	public void insertRiskProject(int rid, int pid) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into risk_project(rid,pid) values(?,?)", new Object[]{rid,pid});
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


}
