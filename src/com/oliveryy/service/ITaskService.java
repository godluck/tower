package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

public interface ITaskService {
	
	public boolean assignTask(String taskId, String[] userIds, String groupId);
	
	public boolean deleteTask(String TaskId);
	
	boolean createTask(String taskName, String groupId,String detail, Date deadline);
	public boolean finishTask(String taskId);
	public Map[] getUTasks(String userid);
	public Map[] getGTasks(String groupid);
}
