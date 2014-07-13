package com.oliveryy.action;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.oliveryy.service.IGroupService;
import com.oliveryy.service.IUserService;

public class IprojectsAction extends BaseAction {
	@Autowired
	private IGroupService groupService;
	@Autowired
	private IUserService userService;
	public String excute(){
		String id=getSession().get("id").toString();
		Map user=userService.getUserInfo(id);
		Map[] groups=groupService.getGroups();
		JSONObject juser=JSONObject.fromObject(user);
		JSONArray jgroups=JSONArray.fromObject(groups);
		String result="{id:"+id+",userinfo:"+juser.toString()+",groups:"+jgroups.toString()+"}";
		getWriter().write(result);
		return null;
	}
}
