package com.oliveryy.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

import javax.servlet.jsp.jstl.sql.Result;
import javax.servlet.jsp.jstl.sql.ResultSupport;
import javax.sql.DataSource;

//IDbhelper implement
public class DbHelperImpl implements IDbhelper {
	private DataSource dataSource;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	private Connection getConnection() {
		try {

			Connection conn = dataSource.getConnection();
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	// select method with parameters
	public Map[] runSelect(String sql, Object[] params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			if (rs.first() == true) {
				rs.previous();
				Result result = ResultSupport.toResult(rs);
				Map[] rows = result.getRows();
				return rows;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// select method without parameters
	public Map[] runSelect(String sql) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.first() == true) {	
				rs.previous();
				Result result = ResultSupport.toResult(rs);
				Map[] rows = result.getRows();
				return rows;
			} else {
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// update and delete methods without parameters
	public void runUpdate(String sql) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getConnection();
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		try {
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// update and delete methods with parameters
	public void runUpdate(String sql, Object[] params) throws Exception {
		Connection conn = null;
		PreparedStatement ps = null;
		conn = getConnection();
		ps = conn.prepareStatement(sql);
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
		ps.executeUpdate();
		try {
			ps.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResultSet doSelect(String sql, Object[] param) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = getConnection();
		try {
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < param.length; i++) {
				ps.setObject(i + 1, param[i]);
			}
			rs = ps.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public ResultSet doSelect(String sql) {
		Connection conn = null;
		Statement s = null;
		ResultSet rs = null;
		conn = getConnection();
		try {
			s = conn.createStatement();
			rs = s.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			//
			e.printStackTrace();
			return null;
		}
	}

}