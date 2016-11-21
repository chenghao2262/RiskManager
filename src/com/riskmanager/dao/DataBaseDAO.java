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

    public List<Object[]> getAllrisk() {
        // dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据

        QueryRunner queryRunner = new QueryRunner();
        Connection connection=utils.getConnection();
        try {
            List<Object[]> list = queryRunner.query(connection, "select * from risk left join risk_detail  on risk.rid=risk_tracker.rid order by risk.rid",
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

	public List<Object[]> getAllrisk(int pid) {
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据

		QueryRunner queryRunner = new QueryRunner();
		Connection connection=utils.getConnection();
		try {
			List<Object[]> list = queryRunner.query(connection, "select * from risk left join risk_detail  on risk.rid=risk_tracker.rid  where risk.pid=? order by risk.rid",
					new ArrayListHandler(),pid);
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
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(sql);    //创建数据库连接对象
			System.out.println("debug:");
			queryRunner.update(connection, "delete from risk where rid=?",new Object[]{rid});
			queryRunner.update(connection, "delete from risk_tracker where rid=?",new Object[]{rid});
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public RiskBean getRiskBean(int rid) {
		return null;
	}

	public void update(int rid, String riskTitle, String riskPossibility, String riskInfluence, String content, String threshold, String newTracker) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(sql);    //创建数据库连接对象
			queryRunner.update(connection, "update risk set riskTitle=?,riskPossibility=?,riskInfluence=?,threshold=?,content=?  where rid=?",new Object[]{riskTitle,riskPossibility,riskInfluence,threshold,content, rid});
			queryRunner.update(connection, "insert into risk_tracker values(?,?)",new Object[]{rid,newTracker});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}     //加载JDBC驱动
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void insert(String riskTitle, String riskPossibility, String riskInfluence, String content, String threshold) {
		QueryRunner queryRunner = new QueryRunner();
		Connection connection=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(sql);    //创建数据库连接对象
			queryRunner.update(connection, "insert into risk(riskTitle,riskPossibility,riskInfluence,threshold,content,creator) values(?,?,?,?,?,?)",new Object[]{riskTitle,riskPossibility,riskInfluence,content,threshold,webContext.getUserName()});
			queryRunner.update(connection, "insert into risk_tracker values(last_insert_id(),?)",new Object[]{webContext.getUserName()});
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}     //加载JDBC驱动
		catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int insertProject(ProjectBean projectBean){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into project(name,creator,createTime) values(?,?,?)", new Object[]{projectBean.getName(),projectBean.getCreator(),projectBean.getCreateTime()});
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



	public int insertRisk(RiskBean riskBean){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into risk(creator,createTime) values(?,?,?)", new Object[]{riskBean.getCreator(),riskBean.getCreateTime()});
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

	public void insertRiskDetail(RiskDetailBean riskDetailBean){
		QueryRunner queryRunner = new QueryRunner();
		Connection connection = utils.getConnection();
		try {
			queryRunner.update(connection,"insert into risk_detail(rid,createTime,updater,riskTitle,riskPossibility,riskInfluence,threshold,content) values(?,now(),?,?,?,?,?,?)", new Object[]{riskDetailBean.getRid(),riskDetailBean.getUpdater(),riskDetailBean.getRiskTitle(),riskDetailBean.getRiskPossibility(),riskDetailBean.getRiskInfluence(),riskDetailBean.getThreshold(),riskDetailBean.getContent()});
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
