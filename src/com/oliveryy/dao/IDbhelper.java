package com.oliveryy.dao;

import java.sql.ResultSet;
import java.util.Map;

public interface IDbhelper {
	public ResultSet doSelect(String sql, Object[] params);

	public ResultSet doSelect(String sql);
	
	public Map[] runSelect(String sql, Object[] params);

	public Map[] runSelect(String sql);

	public void runUpdate(String sql);

	public void runUpdate(String sql, Object[] params);

}
