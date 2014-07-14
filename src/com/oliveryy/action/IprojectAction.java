package com.oliveryy.action;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.IFileService;
import com.oliveryy.service.IGroupService;
import com.oliveryy.service.ITaskService;
import com.oliveryy.service.IUserService;

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
	public String excute(){
		String id=getSession().get("id").toString();
		Map user=userService.getUserInfo(id);
		String groupId=user.get("group_id").toString();
		Map[] tasks=taskService.getUTasks(groupId);
		for(int i=0;i<tasks.length;i++){
			String dl=tasks[i].get("task_deadline").toString();
			DateFormat df=new SimpleDateFormat();
			try {
				Date dld=df.parse(dl);
				Date now=new Date();
				if(dld.after(now)){
					tasks[i].put("delayed", false);
				}else{
					tasks[i].put("delayed", true);
				}
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Map[] discussions=discussionService.getDiscussions(groupId,"d");
		Map[] files=fileService.getFileList(groupId);
		JSONObject juser=JSONObject.fromObject(user);
		JSONArray jtasks=JSONArray.fromObject(tasks);
		JSONArray jdiscussions=JSONArray.fromObject(discussions);
		JSONArray jfiles=JSONArray.fromObject(files);
		String result="{id:"+id+",userinfo:"+juser.toString()+",tasks:"+jtasks.toString()+",discussions:"+jdiscussions.toString()+",files:"+jfiles.toString()+"}";
		getWriter().write(result);
		return null;
	}
}
