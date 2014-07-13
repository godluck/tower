package com.oliveryy.service;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
import com.oliveryy.pojo.User;
@Component
public class GroupService implements IGroupService {
	@Autowired
	private IDbhelper dao;
	@Override
	public boolean addMember(String userId, String groupId) {
		// TODO Auto-generated method stub
		try{
			String sql = "update user set group_id = ? where user_id = ?";
			Object[] param = { groupId, userId };
			dao.runUpdate(sql, param);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean deleteMember(String userId) {
		try{
			String sql = "update user set group_id = null where user_id = ?";
			Object[] param = { userId };
			dao.runUpdate(sql, param);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map getLeader(String groupId) {
		// TODO Auto-generated method stub
		String sql = "select * from user where group_id = ? and user_role = 2";
		Object[] param = { groupId };
		return dao.runSelect(sql, param)[0];
	}

	@Override
	public Map getAdmin(String groupId) {
		String sql = "select * from user where group_id = ? and user_role = 1";
		Object[] param = { groupId };
		return dao.runSelect(sql, param)[0];
	}

	@Override
	public Map[] getMember(String groupId) {
		String sql = "select * from user where group_id = ? and user_role = 3";
		Object[] param = { groupId };
		return dao.runSelect(sql, param);
	}

	@Override
	public boolean setProject(String groupId, String projName) {
		try{
			String sql = "update group set group_name = ? where groupId = ?";
			Object[] param = { projName, groupId };
			dao.runUpdate(sql, param);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map[] getGroups() {
		String sql="select * from groups";
		return dao.runSelect(sql);
	}

}
