package com.oliveryy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;

@Component
public class LoginService implements ILoginService {
	@Autowired
	private IDbhelper dao;

	@Override
	public int canLogin(String userName, String password) {
		String sql = "select user_password,user_role from user where user_id=?";
		Object[] params = { userName };
		Map[] rows = dao.runSelect(sql, params);
		if (rows != null && rows.length == 1) {
			if (rows[0].get("user_password").toString().equals(password)) {
				return Integer.parseInt(rows[0].get("user_role").toString());
			} else {
				return -1;
			}
		} else {
			return -2;
		}
	}

	@Override
	public boolean register(String nickName, String userName, String password) {
		String test = "select count(*) n from user where user_id=?";
		Object[] p = { userName };
		Map[] rows = dao.runSelect(test, p);
		int n= Integer.parseInt(rows[0].get("n").toString());
		if (n == 0) {
			String sql = "insert into user values(?,?,?,3,null)";
			Object[] params = { userName, nickName, password };
			try {
				dao.runUpdate(sql, params);
			} catch (Exception e) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

}
