package com.oliveryy.dao;

import java.util.Map;

//interface IDbhelper
public interface IDbhelper {

	public Map[] runSelect(String sql,Object[] params);
	public Map[] runSelect(String sql);
	public void runUpdate(String sql);
	public void runUpdate(String sql,Object[] params);
}
