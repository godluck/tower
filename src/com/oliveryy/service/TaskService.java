package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
@Component
public class TaskService implements ITaskService {
	@Autowired
	private IDbhelper dao;
	@Override
	public boolean createTask(String taskName, String groupId,String detail, Date deadline) {
		try{
			String sql="insert into task values(null,?,?,?,?,0)";
			Object[] params={groupId,taskName,deadline,detail};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean assignTask(String taskId, String[] userIds, String groupId) {
		try{
			String sql="insert into task_assign values(?,?,?)";
			for(int i=0;i<userIds.length;i++){
				Object[] params={userIds[i],taskId,groupId};
				dao.runUpdate(sql, params);
			}
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean deleteTask(String TaskId) {
		try{
			String sql="delete from task where task_id=?";
			Object[] params={TaskId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map[] getUTasks(String userid) {
		String sql="select t.group_id,t.task_id,t.task_name,t.task_deadline,t.task_detail,t.task_status from task t,task_assign ta where user_id=? and t.task_id=ta.task_id";
		Object[] params={userid};
		return dao.runSelect(sql, params);
	}

	@Override
	public Map[] getGTasks(String groupid) {
		String sql="select * from task where group_id=?";
		Object[] params={groupid};
		return dao.runSelect(sql, params);
	}

	@Override
	public boolean finishTask(String taskId) {
		try{
			String sql="update task set task_status=1 where task_id=?";
			Object[] params={taskId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

}
