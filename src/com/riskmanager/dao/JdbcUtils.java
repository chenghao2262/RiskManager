package com.riskmanager.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class JdbcUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();

	public Connection getConnection() {
	        try {
	        	
	            return  ds.getConnection();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return null;
	}
}
