package com.oliveryy.action;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.IUserService;

public class IReportAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IDiscussionService discussionService;
	public String excute(){
		String id=getSession().get("id").toString();
		Map user=userService.getUserInfo(id);
		String groupId=user.get("group_id").toString();
		Map[] reports=discussionService.getDiscussions(groupId, "r");
		JSONObject juser=JSONObject.fromObject(user);
		JSONArray jreports=JSONArray.fromObject(reports);
		String result="{id:"+id+",userinfo:"+juser.toString()+",reports:"+jreports.toString()+"}";
		getWriter().write(result);
		return null;
	}
}
