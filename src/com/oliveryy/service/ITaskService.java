package com.oliveryy.service;

import java.sql.Date;

public interface ITaskService {
	
	public boolean assignTask(String taskId, String[] userIds, String groupId);
	
	public boolean deleteTask(String TaskId);
	
	boolean createTask(String taskName, String groupId, Date deadline);

}
