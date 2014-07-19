package com.oliveryy.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		String sql = "select user_id,user_name,group_id,user_role from user where group_id = ? and user_role = 2";
		Object[] param = { groupId };
		return dao.runSelect(sql, param)[0];
	}

	@Override
	public Map getAdmin(String groupId) {
		String sql = "select u.user_id,u.user_name,u.group_id,u.user_role from user u,groups g where g.group_id = ? and g.manager_id=u.user_id";
		Object[] param = { groupId };
		return dao.runSelect(sql, param)[0];
	}

	@Override
	public Map[] getMember(String groupId) {
		String sql = "select user_id,user_name,group_id,user_role from user where group_id = ? and user_role = 3";
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
		String sql="select g.group_id,g.group_status,g.group_name,g.user_id,g.group_discription,g.group_date,g.manager_id,u.user_name from groups g,user u where g.user_id=u.user_id";
		Map[] tasks=dao.runSelect(sql);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("group_date").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("group_date");
				tasks[i].put("group_date",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
	}

	@Override
	public boolean deleteGroup(String groupId) {
		try{
			String sql = "delete from groups where group_id=?";
			Object[] param = { groupId };
			dao.runUpdate(sql, param);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map getGroupInfo(String groupId) {
		String sql="select * from groups where group_id=?";
		Object[] params={groupId};
		Map[] tasks=dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("group_date").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("group_date");
				tasks[i].put("group_date",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks[0];
	}

	@Override
	public boolean setManager(String groupId, String userId) {
		try{
			String sql = "update group set manager_id = ? where groupId = ?";
			Object[] param = { userId, groupId };
			dao.runUpdate(sql, param);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map[] getGroups(String userId) {
		String sql="select * from groups where manager_id=?";
		Object[] params={userId};
		Map[] tasks= dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("group_date").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("group_date");
				tasks[i].put("group_date",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
	}

}
