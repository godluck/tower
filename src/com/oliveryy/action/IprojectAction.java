package com.oliveryy.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.IFileService;
import com.oliveryy.service.IGroupService;
import com.oliveryy.service.ITaskService;
import com.oliveryy.service.IUserService;

@Component
public class IprojectAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IDiscussionService discussionService;
	@Autowired
	private IFileService fileService;
	private String groupid;

	public String execute() {
		String id = getSession().getAttribute("id").toString();
		Map user = userService.getUserInfo(id);
		System.out.println(groupid);
		System.out.println(user.get("group_id"));
		if (groupid == null) {
			if (user.get("group_id") != null
					&& Integer.parseInt(user.get("user_role").toString()) > 1) {
				String groupId = user.get("group_id").toString();
				Map[] tasks = taskService.getGTasks(groupId);
				Map[] discussions = discussionService.getDiscussions(groupId,
						"d");
				Map[] files = fileService.getFileList(groupId);
				Map[] reports = discussionService.getDiscussions(groupId, "r");
				JSONObject juser = JSONObject.fromObject(user);
				JSONArray jtasks = JSONArray.fromObject(tasks);
				JSONArray jdiscussions = JSONArray.fromObject(discussions);
				JSONArray jfiles = JSONArray.fromObject(files);
				JSONArray jreports = JSONArray.fromObject(reports);
				String result = "{\"id\":\"" + id + "\",\"userinfo\":"
						+ juser.toString() + ",\"tasks\":" + jtasks.toString()
						+ ",\"discussions\":" + jdiscussions.toString()
						+ ",\"files\":" + jfiles.toString() + ",\"reports\":"
						+ jreports.toString() + ",\"content\":\"detail\"}";
				getWriter().write(result);
				return null;
			} else {
				Map[] groups = groupService.getGroups();
				Map[] managers = userService.getManagers();
				JSONObject juser = JSONObject.fromObject(user);
				JSONArray jgroups = JSONArray.fromObject(groups);
				JSONArray jmanagers = JSONArray.fromObject(managers);
				String result = "{\"id\":\"" + id + "\",\"userinfo\":"
						+ juser.toString() + ",\"groups\":"
						+ jgroups.toString() + ",\"managers\":"
						+ jmanagers.toString() + ",\"content\":\"list\"}";
				getWriter().write(result);
				return null;
			}
		} else {
			String groupId = groupid;
			Map[] tasks = taskService.getGTasks(groupId);
			Map[] discussions= discussionService.getDiscussions(groupId, "d");
			Map[] files= fileService.getFileList(groupId);
			Map[] reports = discussionService.getDiscussions(groupId, "r");
			JSONObject juser = JSONObject.fromObject(user);
			JSONArray jtasks = JSONArray.fromObject(tasks);
			JSONArray jdiscussions = JSONArray.fromObject(discussions);
			JSONArray jfiles = JSONArray.fromObject(files);
			JSONArray jreports = JSONArray.fromObject(reports);
			String result = "{\"id\":\"" + id + "\",\"userinfo\":"
					+ juser.toString() + ",\"tasks\":" + jtasks.toString()
					+ ",\"discussions\":" + jdiscussions.toString()
					+ ",\"files\":" + jfiles.toString() + ",\"reports\":"
					+ jreports.toString() + ",\"content\":\"detail\"}";
			getWriter().write(result);
			groupid=null;
			return null;
		}
	}

	public String getGroupid() {
		return groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
}
