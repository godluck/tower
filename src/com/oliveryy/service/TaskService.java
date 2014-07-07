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
	public boolean createTask(String taskName, String groupId, Date deadline) {
		try{
			String sql="insert into task values(null,?,?,?,?)";
			Object[] params={groupId,taskName,deadline,deadline};
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
				Object[] params={taskId,userIds[i],groupId};
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

}
