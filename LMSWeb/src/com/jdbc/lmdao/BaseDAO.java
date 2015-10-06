/**
 * BaseDAO.java
 * @author Ziyi Chen 
 * <br> Email: <a href="mailto:ziyi_chen@gcitsolutions.com">ziyi_chen@gcitsolutions.com</a>
 * <br> Created on Sep 29, 2015
 */
package com.jdbc.lmdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class BaseDAO {
	protected abstract Object convertResult(ResultSet rs) throws SQLException;
	
	protected Connection getConnection() throws SQLException {
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://localhost:3306/library", 
				"root", "password");
		return conn;
	}
	
//	protected int save(String query, Object[] values, String key) throws SQLException {
//		PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
//		
//		int count = 1;
//		for(Object obj : values) {
//			stmt.setObject(count, obj);
//			count++;
//		}
//		
//		stmt.executeUpdate();
//		ResultSet rs = stmt.getGeneratedKeys();
//		if (key != null && rs.next()) 
//			return rs.getInt(key);
//		else
//			return -1;
//	}
	
	protected int saveWithId(String query, Object[] values, String key) throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
		
		int count = 1;
		for(Object obj : values) {
			stmt.setObject(count, obj);
			count++;
		}
		
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if(key != null && rs.next())
			return rs.getInt(key);
		else 
			return -1;
	}

	protected void save(String query, Object[] values) throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement(query);
		
		int count = 1;
		for(Object obj : values) {
			stmt.setObject(count, obj);
			count++;
		}
		
		stmt.executeUpdate();
	}
	
	protected Object read(String query, Object[] values) throws SQLException {
		PreparedStatement stmt = getConnection().prepareStatement(query);

		if(values != null) {
			int count = 1;
			for(Object obj : values) {
				stmt.setObject(count, obj);
				count++;
			}
		}
		
		ResultSet rs = stmt.executeQuery();

		return this.convertResult(rs);

	}
}
