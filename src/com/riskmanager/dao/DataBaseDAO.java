package com.riskmanager.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.riskmanager.bean.RiskBean;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.springframework.stereotype.Repository;

import com.riskmanager.bean.UserBean;

@Repository
public class DataBaseDAO {

	@Resource
	private JdbcUtils utils;


	public UserBean getUserBeanByName(String name) {
		QueryRunner queryRunner = new QueryRunner();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver Load Success.");

			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/riskmanager?"
					+ "user=root&password=&useUnicode=true&characterEncoding=UTF8");    //创建数据库连接对象
			System.out.println("debug:");
			UserBean userBean = queryRunner.query(connection, "select * from user where username=?",
					new BeanHandler<>(UserBean.class),new String[]{name});
			System.out.println(userBean);
			return userBean;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}     //加载JDBC驱动
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private List<RiskBean> list;
	private Map<String, Object> dataMap;

	public String getAllrisk() {
		// dataMap中的数据将会被Struts2转换成JSON字符串，所以这里要先清空其中的数据
		dataMap = new HashMap<>();
		return "SUCCESS";
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}
}
