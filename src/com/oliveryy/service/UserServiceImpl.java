package com.oliveryy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.DbHelperImpl;
import com.oliveryy.dao.IDbhelper;
/*
 * Author:oliver
 * */
//UserServiceImpl
@Component(value="userService")
public class UserServiceImpl implements IUserService {
	@Autowired
	private IDbhelper dao;
	//login function
	public boolean login(String uid, String pwd) {
		String sql="select count(*) n from userinfo where nickName=? and Password=?";
		Object[] params={uid,pwd};
		Map row=dao.runSelect(sql, params)[0];
		int n=Integer.parseInt(row.get("n").toString());
		return n==1;
	}
	//register function
	@Override
	public boolean register(String id,String name, String pwd) {
		String sql="insert into userinfo values(?,?,?,0)";
		Object[] params={id,name,pwd};
		dao.runUpdate(sql, params);
		String sql2="select count(*) n from userinfo where userId=?";
		Object[] params2={id};
		Map row=dao.runSelect(sql2, params2)[0];
		int n=Integer.parseInt(row.get("n").toString());
		return n==1;
	}
	//check register name
	@Override
	public boolean registerCheckName(String name) {
		String sql="select count(*) n from userinfo where nickName=?";
		Object[] params={name};
		Map row=dao.runSelect(sql, params)[0];
		int n=Integer.parseInt(row.get("n").toString());
		return n==1;
	}
	//judge is Admin or not
	@Override
	public boolean isAdmin(String nickName) {
		String sql="select count(*) n from userinfo where nickName=? and isAdmin= 1 ";
		Object[] params={nickName};
		Map row=dao.runSelect(sql, params)[0];
		int n=Integer.parseInt(row.get("n").toString());
		return n==1;
	}
}
