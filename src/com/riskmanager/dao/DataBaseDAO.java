package com.riskmanager.dao;

import java.sql.SQLException;

import javax.annotation.Resource;

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
			return queryRunner.query(utils.getConnection(), "select * from user where username=" + name,
					new BeanHandler<>(UserBean.class));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
