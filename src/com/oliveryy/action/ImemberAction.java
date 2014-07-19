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

import com.oliveryy.service.IGroupService;
import com.oliveryy.service.IUserService;
@Component
public class ImemberAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	public String execute() {
		String id = getSession().getAttribute("id").toString();
		Map user = userService.getUserInfo(id);
		if (user.get("group_id") != null&&Integer.parseInt(user.get("user_role").toString())>1) {
			String groupId = user.get("group_id").toString();
			Map[] members=userService.getUserInGroup(groupId);
			Map group=groupService.getGroupInfo(groupId);
			Map manager=userService.getUserInfo(groupService.getAdmin(groupId).get("user_id").toString());
			JSONObject juser = JSONObject.fromObject(user);
			JSONObject jgroup=JSONObject.fromObject(group);
			JSONArray jmembers=JSONArray.fromObject(members);
			JSONObject jmanager=JSONObject.fromObject(manager);
			String result = "{\"id\":\"" + id + "\",\"userinfo\":" + juser.toString()
					+ ",\"group\":"+jgroup.toString()+",\"members\":"+jmembers.toString()+",\"manager\":"+jmanager.toString()+",\"type\":\"member\"}";
			getWriter().write(result);
			return null;
		} else {
			Map[] groups = groupService.getGroups(id);
			String[] results=new String[groups.length];
			for(int i=0;i<groups.length;i++){
				String groupId=groups[i].get("group_id").toString();
				Map[] members=userService.getUserInGroup(groupId);
				Map group=groupService.getGroupInfo(groupId);
				Map manager=userService.getUserInfo(groupService.getAdmin(groupId).get("user_id").toString());
				JSONObject juser = JSONObject.fromObject(user);
				JSONObject jgroup=JSONObject.fromObject(group);
				JSONArray jmembers=JSONArray.fromObject(members);
				JSONObject jmanager=JSONObject.fromObject(manager);
				results[i] = "{\"id\":\"" + id + "\",\"userinfo\":" + juser.toString()
						+ ",\"group\":"+jgroup.toString()+",\"members\":"+jmembers.toString()+",\"manager\":"+jmanager.toString()+"}";
			}
			JSONArray result=JSONArray.fromObject(results);
			getWriter().write(result.toString());
			return null;
		}
	}
}
