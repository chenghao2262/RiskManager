package com.riskmanager.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;

@Repository
public class JdbcUtils {
	private static ComboPooledDataSource ds;

	public Connection getConnection() {
	        try {
	        	if (ds == null){
					ds = new ComboPooledDataSource();
				}
	            return  ds.getConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}

}
