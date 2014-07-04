package com.oliveryy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;

@Component
public class LoginService implements ILoginService{
	@Autowired
	private IDbhelper dao;
	@Override
	public int canLogin(String userName, String password) {
		String sql="select role from user where user_id=? and user_password=?";
		Object[] params={userName,password};
		Map[] rows=dao.runSelect(sql,params);
		if(rows.length<=0||rows.length>1){
			return -1;
		}else{
			return Integer.parseInt(rows[0].get("role").toString());
		}
	}

	@Override
	public boolean register(String nickName, String userName, String password) {
		String test="select count(*) n from user where user_id=?";
		Object[] p={userName};
		Map[] rows=dao.runSelect(test, p);
		int n=Integer.parseInt(rows[0].get("n").toString());
		if(n==0){
		String sql="insert into user values(?,?,?,3,null)";
		Object[] params={userName,nickName,password};
		dao.runUpdate(sql, params);
		return true;
		}else{
			return false;
		}
	}

}
