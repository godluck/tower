package com.oliveryy.action;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IGroupService;
import com.oliveryy.service.IUserService;
@Component
public class IprojectsAction extends BaseAction {
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	public String execute(){
		String id=getSession().getAttribute("id").toString();
		Map user=userService.getUserInfo(id);
		Map[] groups=groupService.getGroups();
		JSONObject juser=JSONObject.fromObject(user);
		JSONArray jgroups=JSONArray.fromObject(groups);
		String result="{\"id\":"+id+",\"userinfo\":"+juser.toString()+",\"groups\":"+jgroups.toString()+"}";
		getWriter().write(result);
		return null;
	}
}
