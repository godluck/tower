package com.oliveryy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
@Component
public class UserService implements IUserService {
	@Autowired
	private IDbhelper dao;
	@Override
	public Map getUserInfo(String userId) {
		// TODO Auto-generated method stub
		String sql="select user_id,user_name,group_id,role from user where user_id=?";
		Object[] params={userId};
		Map[] rows=dao.runSelect(sql, params);
		return rows[0];
	}

	@Override
	public boolean updateNickname(String userId, String newUserNickname) {
		// TODO Auto-generated method stub
			try{
				String sql="update table user set user_name=? where user_id=?";
				Object[] params={newUserNickname,userId};
				dao.runUpdate(sql, params);
				}catch(Exception e){
					return false;
				}
			return true;
	}

	@Override
	public boolean updatePassword(String userId, String newPassword) {
		// TODO Auto-generated method stub
		try{
			String sql="update table user set user_password=? where user_id=?";
			Object[] params={newPassword,userId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public int setUpNewGroup(String userId,String groupName,String groupDescription) {
		// TODO Auto-generated method stub
		try{
			String sql="insert into groups values(null,?,0,?,?)";
			Object[] params={groupName,userId,groupDescription};
			dao.runUpdate(sql, params);
			String sql2="select group_id from groups where user_id=?";
			Object[] params2={userId};
			Map[] rows=dao.runSelect(sql2,params2);
			String g_id=rows[0].get("m").toString();
			String sql3="update table user set group_id=? where user_id=?";
			Object[] params3={g_id,userId};
			dao.runUpdate(sql3, params3);
			return Integer.parseInt(g_id);
			}catch(Exception e){
				return -1;
			}
	}

	@Override
	public boolean joinGroup(String userId, String groupId) {
		try{
			String sql="update table user set group_id=? where user_id=?";
			Object[] params={groupId,userId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

}
