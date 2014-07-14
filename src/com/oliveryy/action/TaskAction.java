package com.oliveryy.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.oliveryy.action.BaseAction;
import com.oliveryy.service.ITaskService;
import com.oliveryy.service.IUserService;

public class TaskAction extends BaseAction {
	@Autowired
	private ITaskService taskService;
	private String userId;
	@Autowired
	private IUserService userService;
	private String id = getSession().get("id").toString();
	private int role = Integer.parseInt(userService.getUserInfo(id)
			.get("user_role").toString());
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
		if (role < 3) {
			SimpleDateFormat df = new SimpleDateFormat();
			Date dl;
			try {
				dl = (Date) df.parse(deadline);
				if (taskService.createTask(taskName, groupId, taskDetail, dl)) {
					getWriter().write("success");
				} else {
					getWriter().write("failed");
				}
				;
			} catch (ParseException e) {
				getWriter().write("dateFormatError");
				e.printStackTrace();
			}
		} else {
			getWriter().write("insufficient privileges");
		}
		return null;

	}

	public String assignTask() {
		if (role < 3) {
			String[] temp = { userId };
			if (taskService.assignTask(taskId, temp, groupId)) {
				getWriter().write("success");
			} else {
				getWriter().write("failed");
			}
		} else {
			getWriter().write("insufficient privileges");
		}
		return null;
	}

	public String finishTask() {
		if (taskService.finishTask(taskId)) {
			getWriter().write("success");
		} else {
			getWriter().write("failed");
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
