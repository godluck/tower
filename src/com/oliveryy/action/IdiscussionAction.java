package com.oliveryy.action;

import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.IGroupService;
import com.oliveryy.service.IUserService;
@Component
public class IdiscussionAction extends BaseAction{
	@Autowired
	private IDiscussionService discussionService;
	@Autowired
	private IUserService userService;
	private String disId;
	public String excute(){
		String id=getSession().getAttribute("id").toString();
		Map user=userService.getUserInfo(id);
		Map[] replys=discussionService.getDetail(disId);
		Map dis=discussionService.getDiscussion(disId,"d");
		JSONObject juser=JSONObject.fromObject(user);
		JSONObject jdis=JSONObject.fromObject(dis);
		JSONArray jreplys=JSONArray.fromObject(replys);
		String result="{id:"+id+",userinfo:"+juser.toString()+",discussion:"+jdis.toString()+",replys:"+jreplys.toString()+"}";
		getWriter().write(result);
		return null;
	}
	public String getDisId() {
		return disId;
	}
	public void setDisId(String disId) {
		this.disId = disId;
	}
}
