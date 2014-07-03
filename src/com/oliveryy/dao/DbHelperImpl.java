package com.oliveryy.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	private Connection getConnection(){		
		try{
			
			Connection conn=dataSource.getConnection();
			return conn;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//select method with parameters
	public Map[] runSelect(String sql,Object[] params){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{			
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}					 	
			rs=ps.executeQuery();
			Result result=ResultSupport.toResult(rs);
			Map[] rows=result.getRows();			
			return rows;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				rs.close();
				ps.close();				
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}				
	}
	//select method without parameters
	public Map[] runSelect(String sql){
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try{			
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			rs=ps.executeQuery();
			Result result=ResultSupport.toResult(rs);
			Map[] rows=result.getRows();			
			return rows;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}finally{
			try{
				rs.close();
				ps.close();				
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}				
	}
	//update and delete methods without parameters
	public void runUpdate(String sql){
		Connection conn=null;
		PreparedStatement ps=null;
		try{			
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				ps.close();				
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}				
	}
	//update and delete methods with parameters
	public void runUpdate(String sql,Object[] params){
		Connection conn=null;
		PreparedStatement ps=null;
		try{			
			conn=getConnection();
			ps=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}	
			ps.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try{
				ps.close();				
				conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}				
	}





		
}
