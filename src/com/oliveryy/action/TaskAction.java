package com.oliveryy.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.action.BaseAction;
import com.oliveryy.service.ITaskService;
import com.oliveryy.service.IUserService;
@Component
public class TaskAction extends BaseAction {
	@Autowired
	private ITaskService taskService;
	private String userId;
	@Autowired
	private IUserService userService;
	private String id;
	private int role;
	private String taskId;
	private String taskName;
	private String groupId;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	private String taskDetail;
	private String deadline;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskDetail() {
		return taskDetail;
	}

	public void setTaskDetail(String taskDetail) {
		this.taskDetail = taskDetail;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String addTask() {
		id=getSession().getAttribute("id").toString();
		role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
		if (role < 3) {
			SimpleDateFormat df = new SimpleDateFormat();
			Date dl;
			try {
				dl = (Date) df.parse(deadline);
				if (taskService.createTask(taskName, groupId, taskDetail, dl)) {
					getWriter().write("{\"error\":0}");
				} else {
					getWriter().write("{\"error\":1,\"reason\":\"failed to create new task\"}");
				}
				;
			} catch (ParseException e) {
				getWriter().write("{\"error\":1,\"reason\":\"wrong date format\"}");
				e.printStackTrace();
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"insufficient privileges\"}");
		}
		return null;

	}

	public String assignTask() {
		id=getSession().getAttribute("id").toString();
		role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
		if (role < 3) {
			String[] temp = { userId };
			if (taskService.assignTask(taskId, temp, groupId)) {
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to assign task\"}");
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"insufficient privileges\"}");
		}
		return null;
	}

	public String finishTask() {
		if (taskService.finishTask(taskId)) {
			getWriter().write("{\"error\":0}");
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"failed to finish task\"}");
		}
		;
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTaskId() {
		return taskId;
	}

	public void setTaskId(String taskId) {
		this.taskId = taskId;
	}
}
