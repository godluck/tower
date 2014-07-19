package com.oliveryy.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsDateJsonValueProcessor;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.ITaskService;
import com.oliveryy.service.IUserService;

@Component
public class ImyselfAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private ITaskService taskService;
	@Autowired
	private IDiscussionService discusstionService;

	public String execute() {
		String id = getSession().getAttribute("id").toString();
		Map user = userService.getUserInfo(id);
		Map[] tasks = taskService.getUTasks(id);
		Map[] reports = discusstionService.getUserReports(id, "r");
		JSONObject juser = JSONObject.fromObject(user);
		JSONArray jtask = JSONArray.fromObject(tasks);
		JSONArray jreports = JSONArray.fromObject(reports);
		String result = "{\"id\":\"" + id + "\",\"userinfo\":" + juser.toString()
				+ ",\"tasks\":" + jtask.toString() + ",\"reports\":"
				+ jreports.toString() + "}";
		getWriter().write(result);
		return null;
	}
}
