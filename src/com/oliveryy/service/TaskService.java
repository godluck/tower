package com.oliveryy.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
		String sql="select t.group_id,t.task_id,t.task_name,t.task_deadline,t.task_detail,t.task_status,g.group_name from task t,task_assign ta,groups g where ta.user_id=? and t.task_id=ta.task_id and t.group_id=g.group_id";
		Object[] params={userid};
		Map[] tasks=dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("task_deadline").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date dld = df.parse(dl);
				java.util.Date now = new java.util.Date();
				tasks[i].remove("task_deadline");
				tasks[i].put("task_deadline",dld);
				if (dld.after(now)) {
					tasks[i].put("delayed", false);
				} else {
					tasks[i].put("delayed", true);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
	}

	@Override
	public Map[] getGTasks(String groupid) {
		String sql="select t.group_id,t.task_id,t.task_name,t.task_deadline,t.task_detail,t.task_status,g.group_name from task t,groups g where t.group_id=? and t.group_id=g.group_id";
		Object[] params={groupid};
		Map[] tasks=dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("task_deadline").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			try {
				java.util.Date dld = df.parse(dl);
				java.util.Date now = new java.util.Date();
				tasks[i].remove("task_deadline");
				tasks[i].put("task_deadline",dld);
				if (dld.after(now)) {
					tasks[i].put("delayed", false);
				} else {
					tasks[i].put("delayed", true);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
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
